organization := "com.example"

name := "greg.me backend"

version := "0.1.0"

//scalaVersion := "2.9.2"
scalaVersion := "2.10.0-RC2"

seq(webSettings :_*)

resolvers += "Sonatype Releases" at "https://oss.sonatype.org/content/groups/public/"

libraryDependencies += "net.debasishg" %% "redisclient" % "2.9"

libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % "9.0.0.M4"

libraryDependencies ++= Seq(
  	//"org.scalatra" %% "scalatra" % "2.0.3",
  	"ch.qos.logback" % "logback-classic" % "1.0.0" % "runtime",
  	"org.eclipse.jetty" % "jetty-webapp" % "7.5.4.v20111024" % "container",
  	"javax.servlet" % "servlet-api" % "2.5" % "provided",
 	"javax.ws.rs" % "jsr311-api" % "1.1" % "provided" ,
    "com.sun.jersey" % "jersey-core" % "1.2",
    "com.sun.jersey" % "jersey-server" % "1.2",
	"com.sun.jersey" % "jersey-json" % "1.2",
  	"org.codehaus.jackson" % "jackson-core-asl" % "1.8.1",
  	"org.codehaus.jackson" % "jackson-mapper-asl" % "1.8.1",
  	"org.codehaus.jackson" % "jackson-jaxrs" % "1.8.1",
  	"org.codehaus.jackson" % "jackson-xc" % "1.8.1",
   	"javax.persistence" % "persistence-api" % "1.0",
   	"org.eclipse.persistence" % "eclipselink" % "2.0.0",
   	"javax.mail" % "mail" % "1.4.5-rc1",
   	"com.h2database" % "h2" % "1.3.164",
   	"com.orientechnologies" % "orient-commons" % "1.2.0",
   	"com.orientechnologies" % "orientdb-core" % "1.2.0",
   	"org.apache.httpcomponents" % "httpclient" % "4.2.2"
)

resolvers += "spray repo" at "http://repo.spray.io"

libraryDependencies ++= Seq(
	"io.spray" % "spray-caching" % "1.1-M5",
	"io.spray" % "spray-can" % "1.1-M5",
	"io.spray" % "spray-client" % "1.1-M5",
	"io.spray" % "spray-http" % "1.1-M5",
	"io.spray" % "spray-io" % "1.1-M5",
	"io.spray" % "spray-routing" % "1.1-M5",
	"io.spray" % "spray-util" % "1.1-M5"
)

seq(Revolver.settings: _*)


resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies +=
  "com.typesafe.akka" %% "akka-actor" % "2.1.0-RC2" cross CrossVersion.full

libraryDependencies += "com.novocode" % "junit-interface" % "0.10-M1" % "test"


resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

resolvers += "EclipseLink Repo" at "http://download.eclipse.org/rt/eclipselink/maven.repo"
