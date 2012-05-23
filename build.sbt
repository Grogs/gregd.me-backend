organization := "com.example"

name := "scalatra-sbt-prototype"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.9.2"

seq(webSettings :_*)

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
   "com.h2database" % "h2" % "1.3.164"
)

resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"

resolvers += "EclipseLink Repo" at "http://download.eclipse.org/rt/eclipselink/maven.repo"
