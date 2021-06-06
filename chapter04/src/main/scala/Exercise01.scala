import zio.{ App, ExitCode, UIO, URIO, ZEnv, ZIO }

/**
 * 1. Using the appropriate effect constructor, fix the following function so that
 * it no longer fails with defects when executed. Make a note of how the
 * inferred return type for the function changes.
 *
 * def failWithMessage(string: String) = ZIO.succeed(throw new Error(string))
 */
object Exercise01 extends App {

  def failWithMessage(string: String): UIO[Unit] =
    ZIO
      .effect(throw new Error(string))
      .catchAll { e =>
        ZIO.effectTotal(e.printStackTrace())
      }

  override def run(args: List[String]): URIO[ZEnv, ExitCode] =
    failWithMessage("Data Not found").exitCode
}
