lazy val root = (project in file(".")).enablePlugins(SbtWeb)

JsEngineKeys.engineType := JsEngineKeys.EngineType.Node

pipelineStages := Seq(autoprefixer)

AutoprefixerKeys.browsers := com.typesafe.sbt.web.js.JS.Array("safari 5")

val checkCSSFileContents = taskKey[Unit]("check that css contents are correct")

checkCSSFileContents := {
  val contents = IO.read(file("target/web/stage/css/test.css"))
  if (!contents.contains("-webkit-transition")) {
    sys.error(s"Unexpected contents: $contents")
  }
}