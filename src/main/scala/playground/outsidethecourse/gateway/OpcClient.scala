package playground.outsidethecourse.gateway

import java.util
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong
import java.util.function.BiConsumer
import java.util.stream.Collectors

import akka.NotUsed
import akka.actor.{Actor, ActorRef}
import akka.stream.scaladsl.{Flow, Sink}
import akka.stream.typed.scaladsl.ActorSink
import org.eclipse.milo.opcua.sdk.client.OpcUaClient
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig
import org.eclipse.milo.opcua.sdk.client.api.identity.AnonymousProvider
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.{UaMonitoredItem, UaSubscription}
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaSubscriptionManager
import org.eclipse.milo.opcua.stack.client.UaTcpStackClient
import org.eclipse.milo.opcua.stack.core.AttributeId
import org.eclipse.milo.opcua.stack.core.types.builtin._
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint
import org.eclipse.milo.opcua.stack.core.types.enumerated.{MonitoringMode, TimestampsToReturn}
import org.eclipse.milo.opcua.stack.core.types.structured.{MonitoredItemCreateRequest, MonitoringParameters, ReadValueId}
import playground.outsidethecourse.gateway.OpcClient.Client._

object OpcClient{
	
	object Client{
		case object Init
		case object Stop
		case class RequestNodeData(nodeId: String)
		case class Subscription(nodeId: String, samplingInterval: Double, ref: ActorRef)
		case class Message(nodeId: String, value: Any)
		case class DeleteSubscription(nodeId: NodeId, samplingInterval: Double)
	}
	
	class Client extends Actor{
		import Client._
		override def receive: Receive = initClient()
		
		def initClient(): Receive ={
			case Init =>
				val opcConfig: OpcUaClientConfig = OpcUaClientConfig.builder()
						.setApplicationName(LocalizedText.english("eclipse milo opc-ua client"))
						.setApplicationUri("urn:eclipse:milo:client")
						.setEndpoint(UaTcpStackClient.getEndpoints("opc.tcp://localhost:49320")
								.get()
								.apply(0))
						.setIdentityProvider(new AnonymousProvider())
						.setRequestTimeout(UInteger.valueOf(5000)).build()
				val opcClient = new OpcUaClient(opcConfig)
				opcClient.connect().get(10000, TimeUnit.MILLISECONDS)
				context.become(initializedClient(opcClient, opcClient.getSubscriptionManager, new AtomicLong(1L), Map[Double, UaSubscription]()))
		}
		
		def initializedClient(opcClient: OpcUaClient, manager: OpcUaSubscriptionManager, atomicLong: AtomicLong, map: Map[Double, UaSubscription]) : Receive = {
			
			case RequestNodeData(nodeId) =>
				val sample = opcClient.getAddressSpace.createVariableNode(NodeId.parse(nodeId)).readValue().get(2000, TimeUnit.MILLISECONDS).getValue.getValue
				sender() ! Message(nodeId, sample)
				
			case Subscription(nodeId, samplingInterval, ref) =>
				val subscription: Double => UaSubscription = doubleValue =>
					if (map.get(doubleValue).isEmpty) {
						val createdSubscription = manager.createSubscription(doubleValue).get()
						context.become(initializedClient(opcClient, manager, atomicLong, map + (doubleValue -> createdSubscription)))
						createdSubscription
					}
					else map.apply(doubleValue)
				val readValueId = new ReadValueId(NodeId.parse("ns=2;s="+nodeId), AttributeId.Value.uid, null, QualifiedName.NULL_VALUE)
				val parameters = new MonitoringParameters(uint(atomicLong.getAndIncrement()), samplingInterval, null, uint(0), false)
				val request: util.ArrayList[MonitoredItemCreateRequest] = new util.ArrayList()
				request.add(new MonitoredItemCreateRequest(readValueId, MonitoringMode.Reporting, parameters))
				val onItemCreated: BiConsumer[UaMonitoredItem, Integer] = (item: UaMonitoredItem, id: Integer) =>
					if (item.getStatusCode.isGood){
						item.setValueConsumer((item: UaMonitoredItem, value: DataValue) =>
						ref ! Message(item.getReadValueId.getNodeId.getIdentifier.toString, value.getValue.getValue))
					}
					else println(s"Failed to create monitored item from: ${item.getReadValueId.getNodeId}")
				subscription(samplingInterval).createMonitoredItems(TimestampsToReturn.Server, request, onItemCreated)
				println(manager.getSubscriptions.size())
				
			case DeleteSubscription(nodeId, samplingInterval) =>
				val uaSubscription = map.apply(samplingInterval)
				uaSubscription.deleteMonitoredItems(
					uaSubscription.getMonitoredItems
							.stream
							.filter(x => x.getReadValueId.getNodeId.equals(nodeId))
							.collect(Collectors.toList()))
			
			case Stop =>
				opcClient.disconnect()
				context.stop(self)
		}
	}
}
