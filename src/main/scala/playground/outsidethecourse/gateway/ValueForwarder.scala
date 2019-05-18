package playground.outsidethecourse.gateway

import akka.actor.AbstractActor.Receive
import akka.actor.Actor
import akka.stream.actor.ActorPublisher
import akka.stream.scaladsl.Source
import playground.outsidethecourse.gateway.OpcClient.Client.Message
import playground.outsidethecourse.gateway.ValueForwarder.Subscribe

import scala.compat.java8.functionConverterImpls.{AsJavaBiConsumer, AsJavaConsumer}

object ValueForwarder{
	case object Init
	case object Ack
	case class Subscribe(nodeId: String, samplingInterval: Double)
	
}

class ValueForwarder extends Actor  {
	override def receive(): Receive =  {
		case Subscribe(nodeId: String, samplingInterval: Double) =>
		
		
	}
	
//	val source = Source(() => { s"Message"})
//			.map { msg =>
//				println(s"Sender: sending $msg")
//			}
//	val completeFlow = source.to(sink).run()
}