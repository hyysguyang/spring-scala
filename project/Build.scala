import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import sbt.Keys._
import sbt._

/**
 * @author Young Gu
 */
object ProjectBuild extends Build {

    val VERSION = "0.0.1-SNAPSHOT"
    val lifecycle=addCommandAlias("install", ";test;it:test;publishLocal") ++ addCommandAlias("testing", ";test;it:test")

    val basicSettings = Defaults.coreDefaultSettings ++ lifecycle ++ Seq(
        version := VERSION,
        homepage := Some(new URL("https://lifecosys.com/developer/lifecosys-customer-assistant")),
        organization := "com.lifecosys",
        organizationHomepage := Some(new URL("https://lifecosys.com")),
        description := "Lifecosys customer assistant.",
        startYear := Some(2015),
        scalaVersion := "2.11.4",
        scalacOptions := Seq(
            "-encoding", "utf8",
            "-feature",
            "-unchecked",
            "-deprecation",
            "-target:jvm-1.6",
            "-language:postfixOps",
            "-language:implicitConversions",
            "-Xlog-reflective-calls"
        ),
        publishMavenStyle := true
    )

    import scalariform.formatter.preferences._
    import com.typesafe.sbt.SbtScalariform._

    val formattingSettings = scalariformSettings ++ Seq(
        ScalariformKeys.preferences := ScalariformKeys.preferences.value
                .setPreference(RewriteArrowSymbols, true)
                .setPreference(IndentSpaces, 4)
                .setPreference(AlignParameters, true)
                .setPreference(AlignSingleLineCaseStatements, true)
                .setPreference(DoubleIndentClassDeclaration, true)
                .setPreference(PreserveDanglingCloseParenthesis, true))


    import net.virtualvoid.sbt.graph.Plugin._
//
    lazy val projectBuildSettings = basicSettings //++ formattingSettings ++ graphSettings


    lazy val root = Project("spring-scala", file("."))
            .settings(projectBuildSettings: _*)
            .settings(libraryDependencies ++= dependencies)


    val springVersion="3.2.4.RELEASE";
    val dependencies=Seq(
        "org.springframework" % "spring-aop" % springVersion % "provided",
        "org.springframework" % "spring-core" % springVersion % "compile",
        "org.springframework" % "spring-beans" % springVersion % "compile",
        "org.springframework" % "spring-context" % springVersion % "compile",
        "org.springframework" % "spring-jdbc" % springVersion % "provided",
        "org.springframework" % "spring-jms" % springVersion % "provided",
        "org.springframework" % "spring-web" % springVersion % "provided",
        "org.springframework" % "spring-test" % springVersion % "provided",


        "org.scala-lang" % "scala-reflect" % "2.11.4" % "compile",
        "org.scala-lang.modules" %% "scala-xml" % "1.0.2" % "compile",

        "com.fasterxml.jackson.module" %% "jackson-module-scala"  % "2.4.2" % "provided",
        "org.apache.geronimo.specs" % "geronimo-jms_1.1_spec" % "1.1" % "provided",
        "javax.servlet" % "servlet-api" % "2.5" % "provided",
        "javax.inject" % "javax.inject" % "1" % "provided",

        "org.scalatest" %% "scalatest" % "2.2.3" % "test",
        "junit" % "junit" % "4.10" % "test",
        "org.hsqldb" % "hsqldb-j5" % "2.2.4" % "test",
        "log4j" % "log4j" % "1.2.16" % "test",
        "org.springframework" % "spring-aspects" % springVersion % "test"
    )

}
