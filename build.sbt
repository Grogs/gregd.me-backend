organization := "com.example"

name := "scalatra-sbt-prototype"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.9.1"

seq(webSettings :_*)

libraryDependencies ++= Seq(
  val scalatraVersion = "2.0.0"
  "org.scalatra" %% "scalatra" % scalatraVersion,
  "org.scalatra" %% "scalatra-scalate" % scalatraVersion,
  "org.eclipse.jetty" % "jetty-webapp" % "7.4.5.v20110725" % "jetty",
  "javax.servlet" % "servlet-api" % "2.5" % "provided"
)

resolvers += "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
