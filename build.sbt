val scala3Version = "3.5.1"

lazy val root = project
    .in(file("."))
    .settings(
      name := "advent-of-scala",
      version := "0.1.0-SNAPSHOT",
      scalaVersion := scala3Version,
      libraryDependencies ++= Seq(
        "com.softwaremill.sttp.client4" %% "core" % "4.0.0-M13",
        "org.scalameta" %% "munit" % "1.0.0" % Test
      )
    )
