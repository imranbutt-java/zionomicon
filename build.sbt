val testFramework = new TestFramework("zio.test.sbt.ZTestFramework")

lazy val chapter02 = (project in file("chapter02"))
  .settings(
    name := "chapter02",
    scalaVersion := "2.13.6",
    scalaSource := baseDirectory.value / "src/main/scala",
    scalaSource := baseDirectory.value / "src/test/scala",
    libraryDependencies ++= Dependencies.zio,
    testFrameworks += testFramework
  )

lazy val chapter04 = (project in file("chapter04"))
  .settings(
    name := "chapter04",
    scalaVersion := "2.13.6",
    scalaSource := baseDirectory.value / "src/main/scala",
    scalaSource := baseDirectory.value / "src/test/scala",
    libraryDependencies ++= Dependencies.zio,
    testFrameworks += testFramework
  )

lazy val root = project
  .in(file("."))
  .aggregate(chapter04)
  .settings(
    name := "zionomicon",
    version := "0.1.0",
    scalacOptions ++= Seq(
      "-feature",
      "-language:_",
      "-unchecked",
      "-Ymacro-annotations",
      "-language:postfixOps"
    ),
    scalaVersion := "2.13.6"
  )
