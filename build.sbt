ThisBuild / tlBaseVersion := "1.0"

ThisBuild / organization := "com.armanbilge"
ThisBuild / organizationName := "Scala Native Polling Execution Context Scheduler Working Group"
ThisBuild / developers ++= List(
  tlGitHubDev("armanbilge", "Arman Bilge")
)
ThisBuild / startYear := Some(2022)
ThisBuild / tlSonatypeUseLegacyHost := false

val scala213 = "2.13.9"
ThisBuild / scalaVersion := scala213
ThisBuild / crossScalaVersions := Seq("2.11.12", "2.12.17", scala213, "3.1.3")

lazy val core = project
  .in(file("core"))
  .enablePlugins(ScalaNativePlugin)
  .settings(
    name := "scala-native-polling-execution-context-scheduler"
  )
