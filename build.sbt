ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.14"

lazy val root = (project in file("."))
  .settings(
    name := "receipt-printer"
  )
libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.18" % Test
libraryDependencies += "org.scalamock" %% "scalamock" % "6.0.0" % Test
libraryDependencies += "org.scalamock" %% "scalamock" % "6.0.0" % Test

