import zio.{ App, ExitCode, URIO, ZEnv, ZIO }

/* Created by imransarwar on 06/06/2021 */

/**
 * 2. Using the ZIO#foldCauseM operator and the Cause#defects method, im- plement
 * the following function. This function should take the effect, inspect
 * defects, and if a suitable defect is found, it should recover from the
 * error with the help of the specified function, which generates a new success
 * value for such a defect.
 *
 * def recoverFromSomeDefects[R, E, A]
 * (zio: ZIO[R, E, A])( f: Throwable => Option[A]): ZIO[R, E, A] = ???
 */
object Exercise02 extends App {
  def isSuitable(x: Throwable) = x match {
    case x if x.getMessage == "Ops" => true
    case _                          => false
  }

  def recoverFromSomeDefects[R, E, A](zio: ZIO[R, E, A])(f: Throwable => Option[A]): ZIO[R, E, A] =
    zio.foldCauseM(
      cause =>
        (for {
          suitableDefect <- cause.defects.find(isSuitable)
          expectedOption <- f(suitableDefect)
        } yield expectedOption).fold(zio)(ZIO.succeed(_)),
      _ => zio
    )

  val f = (x: Throwable) ⇒
    if (x.getMessage == "Ops") Option("Got it")
    else Option("Nops")

  override def run(args: List[String]): URIO[ZEnv, ExitCode] =
    URIO
      .succeed(throw new Exception("Ops"))
      .catchSomeCause {
        case _ =>
          recoverFromSomeDefects(ZIO.succeed("Done"))(f)
      }
      .exitCode
}
