import Exercise02.isSuitable
import zio.{ExitCode, URIO, ZEnv, ZIO}

import java.io.FileNotFoundException

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
  def isSuitable: Throwable => Boolean = ???
  def recoverFromSomeDefects[R, E, A](zio: ZIO[R, E, A])(f: Throwable => Option[A]): ZIO[R, E, A] =
    zio.foldCauseM(
      cause =>
        (for {
          suitableDefect <- cause.defects.find(isSuitable)
          expectedOption <- f(suitableDefect)
        } yield expectedOption).fold(zio)(ZIO.succeed(_)),
      _ => zio
    )

  override def run(args: List[String]): URIO[ZEnv, ExitCode] =
  recoverFromSomeDefects(ZIO.succeed("Done")(throw new In("Reason") => Option("Got it"))
}
