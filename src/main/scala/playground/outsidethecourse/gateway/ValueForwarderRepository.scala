package playground.outsidethecourse.gateway

import akka.actor.{Actor, ActorRef}

object ValueForwarderRepository {
	case class SubscribeToValueForwarder(nodeId: String, samplingInterval: Double)
}

class ValueForwarderRepository extends Actor{
	import ValueForwarderRepository._
	override def receive: Receive = repository(Map())
	
	def repository(repo: Map[Double, Map[String, ActorRef]]): Receive ={
		case SubscribeToValueForwarder(nodeId, samplingInterval) =>
			repo.apply(samplingInterval).apply(nodeId)
	}
}
