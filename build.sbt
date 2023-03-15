ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

libraryDependencies ++= Seq(
  "io.cucumber" %% "cucumber-scala" % "6.10.4" % Test,
  "io.cucumber" % "cucumber-junit" % "6.10.4" % Test,
  "junit" % "junit" % "4.13.2" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test,
  "io.qameta.allure" % "allure-cucumber6-jvm" % "2.21.0" % Test,
  "org.seleniumhq.selenium" % "selenium-java" % "4.8.1" % Test,
  "org.assertj" % "assertj-core" % "3.24.2" % Test,
  "org.slf4j" % "slf4j-simple" % "2.0.5" % Test,
  "org.projectlombok" % "lombok" % "1.18.26" % Test
)

lazy val root = (project in file("."))
  .settings(
    name := "sample-scala-cucumber-selenium"
  )
