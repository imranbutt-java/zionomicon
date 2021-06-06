/* Created by imransarwar on 06/06/2021 */
import sbt._
import versions._

object Dependencies {

  val common = Seq(
    )

  val zio = Seq(
    "dev.zio" %% "zio"              % Zio,
    "dev.zio" %% "zio-interop-cats" % CatInterOps,
    "dev.zio" %% "zio-test"         % Zio % Test
  )

  val doobie = Seq(
    "org.tpolecat" %% "doobie-core"   % Doobie,
    "org.tpolecat" %% "doobie-hikari" % Doobie
  )

  val http4s = Seq(
    "org.http4s" %% "http4s-core"         % Http4s,
    "org.http4s" %% "http4s-dsl"          % Http4s,
    "org.http4s" %% "http4s-blaze-server" % Http4s
  )

  val cats = Seq(
    "org.typelevel" %% "cats-core"   % Cats,
    "org.typelevel" %% "cats-effect" % Cats
  )

}
