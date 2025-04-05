package org.fp.ForestwinServer.server

import cats.effect.*
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.implicits.*
import org.http4s.server.Router
import org.fp.ForestTwin.routes.Healthcheck
import com.comcast.ip4s.*
//import com.
object ForestwinServer extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    val httpApp = Router("/" -> Healthcheck.healthRoutes).orNotFound

    EmberServerBuilder.default[IO]
      .withHost(Host.fromString("0.0.0.0").getOrElse(host"0.0.0.0"))
      .withPort(Port.fromInt(8080).getOrElse(port"8080"))
      .withHttpApp(httpApp)
      .build
      .use(_ => IO.never)
      .as(ExitCode.Success)
  }
}