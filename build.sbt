name := "scalastarter"

version := "1.0.4"

scalaVersion := "2.12.7"

// https://mvnrepository.com/artifact/com.typesafe.slick/slick
libraryDependencies += "com.typesafe.slick" %% "slick" % "3.2.3"

// https://mvnrepository.com/artifact/junit/junit
libraryDependencies += "junit" % "junit" % "4.12" % Test

// https://mvnrepository.com/artifact/org.scalatest/scalatest
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test

// https://mvnrepository.com/artifact/org.eclipse.milo/sdk-client
libraryDependencies += "org.eclipse.milo" % "sdk-client" % "0.2.4"

// https://mvnrepository.com/artifact/org.eclipse.milo/stack-core
libraryDependencies += "org.eclipse.milo" % "stack-core" % "0.2.4"

// https://mvnrepository.com/artifact/org.eclipse.milo/sdk-core
libraryDependencies += "org.eclipse.milo" % "sdk-core" % "0.2.4"

lazy val akkaVersion = "2.5.22"

libraryDependencies ++= Seq(
	"com.typesafe.akka" %% "akka-actor" % akkaVersion,
	"com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
	"com.typesafe.akka" %% "akka-stream" % akkaVersion,
	"com.typesafe.akka" %% "akka-stream-typed" % akkaVersion
)

