name := """fp-lesson"""

version := "1.0"

scalaVersion := "2.10.1"

mainClass in Compile := Some("ExampleRunner")

libraryDependencies := Seq(
  "org.scala-lang" % "scala-compiler" % "2.10.1",
  "org.scala-lang" % "scala-reflect" % "2.10.1",
  "org.scala-lang" % "scala-library" % "2.10.1"
)

