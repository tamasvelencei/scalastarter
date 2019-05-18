package playground.outsidethecourse.gateway

import java.time.{Duration, ZonedDateTime}
import java.time.format.DateTimeFormatter
import java.util.function.BiConsumer

import akka.actor.{Actor, ActorRef, Timers}
import akka.stream.actor.{ActorSubscriber, ActorSubscriberMessage}
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue
import playground.outsidethecourse.gateway.OpcClient.Client.{Message, Subscription}


object ValueCollector {
	case object Stop
	case class CollectSamples()
}

class ValueCollector(client: ActorRef) extends Timers{
	import ValueCollector._
	override def receive: Receive = init()
	
	def init(): Receive = {
		case Subscription(nodeId, samplingInterval, consumer) =>
			client ! Subscription(nodeId, samplingInterval, consumer)
			timers.startPeriodicTimer(1, CollectSamples, Duration.ofMillis(samplingInterval.toLong))
			context.become(forwards(Map[String, Any]()))
	}
	
	def forwards(map: Map[String, Any]): Receive = {
		case Subscription(nodeId, samplingInterval, consumer) =>
			client ! Subscription(nodeId, samplingInterval, consumer)
		case Message(nodeId, value) =>
			context.become(forwards(map + (nodeId -> value)))
		case CollectSamples =>
			println(s"${ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))}: mapsize: ${map.size}, collectedMsgs: $map")
		case Stop =>
			context.stop(self)
	}
}

