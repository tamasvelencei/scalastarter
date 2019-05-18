package playground.outsidethecourse.gateway

import akka.NotUsed
import akka.actor.{Actor, ActorSystem, Props}
import akka.stream.actor.ActorPublisher
import akka.stream.{ActorMaterializer, ClosedShape}
import akka.stream.scaladsl.{Broadcast, Flow, GraphDSL, Merge, RunnableGraph, Sink, Source}

object Main extends App{
	
	val system = ActorSystem("OpcUaClient")
//	val client = system.actorOf(Props[Client], "client")
//	println(client.path)
//	val client2 = system.actorSelection("/user/client")
//	client2 ! Init
//
//	val forwarder1 = system.actorOf(Props[ValueCollector], "forwarder1")
//
//	//	client ! Subscription(forwarder1, "Simulation Examples.Functions.Ramp1", 50)
//
//	//	client ! Subscription(forwarder2, new NodeId(2, "Simulation Examples.Functions.Ramp2"), 1000)
//	forwarder1 ! Subscription(client, "Simulation Examples.Functions.Ramp2", 100)
//	forwarder1 ! Subscription(client, "Simulation Examples.Functions.Ramp1", 1000)
	//	import system.dispatcher
	//	system.scheduler.schedule(Duration.Zero, Duration.apply(50, TimeUnit.MILLISECONDS), client, RequestNodeData(forwarder1, "Simulation Examples.Functions.Ramp1"))


//	implicit val system = ActorSystem()
//	implicit val mat = ActorMaterializer()

//	val source = Source(0 to 10)
//
//	val flow = Flow[Int].map("flow1: " + _.toString)
//	val sink = Sink.foreach[String](println(_))
//	val runnable = source.via(flow).to(sink)
//	runnable.run()
//
//	val flow2 = Flow[Int].map("flow2: " + _.toString)
//	val sink2 = Sink.queue()
//	val runnable2 = source.via(flow).to(sink)
//
//
//	runnable2.run()
	
//
//	val g = RunnableGraph.fromGraph(GraphDSL.create() { implicit builder: GraphDSL.Builder[NotUsed] =>
//		import GraphDSL.Implicits._
//		val in = Source(1 to 10)
//		val out = Sink.ignore
//
//		val bcast = builder.add(Broadcast[Int](2))
//		val merge = builder.add(Merge[Int](2))
//
//		val f1, f2, f3, f4 = Flow[Int].map(_ + 10)
//
//		in ~> f1 ~> bcast ~> f2 ~> merge ~> f3 ~> out
//		bcast ~> f4 ~> merge
//		ClosedShape
//	})
//
//	val topHeadSink = Sink.head[Int]
//	val bottomHeadSink = Sink.head[Int]
//	val sharedDoubler = Flow[Int].map(_ * 2)
//
//	RunnableGraph.fromGraph(GraphDSL.create(topHeadSink, bottomHeadSink)((_, _)) { implicit builder =>
//		import GraphDSL.Implicits._
//		(topHS, bottomHS) =>
//			import GraphDSL.Implicits._
//			val broadcast = builder.add(Broadcast[Int](2))
//			Source.single(1) ~> broadcast.in
//
//			broadcast ~> sharedDoubler ~> topHS.in
//			broadcast ~> sharedDoubler ~> bottomHS.in
//			ClosedShape
//	})
	
	case class Weather(zipCode : String, temperature : Double, raining : Boolean)
	
	object WeatherForwarder {
		def props : Props = Props[WeatherForwarder]
	}
	
	//see provided link for example definition
	class WeatherForwarder extends Actor {
		override def receive: Receive = ???
	}
	
	val forwarder = system actorOf WeatherForwarder.props
	
	//note the stream has not been instatiated yet
	forwarder ! Weather("02139", 32.0, true)
	
	//stream already has 1 Weather value to process which is sitting in the
	//ActorRef's internal buffer
	val stream = Source.fromPublisher(ActorPublisher[Weather](forwarder))

	
}
