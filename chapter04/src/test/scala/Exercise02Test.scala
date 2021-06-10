import zio.ZIO
import zio.test.Assertion.equalTo
import zio.test.{ assertM, suite, testM, DefaultRunnableSpec }

/* Created by imransarwar on 11/06/2021 */
object Exercise02Suite {
  import Exercise02._
  val suiteChp5Ex2 = suite("Test ch4, ex2") {
    testM("ex2 I") {
      val theNumberOfTheBeast = 666
      val divBoom             = ZIO.effectTotal(throw new Exception("Ops"))
      val reco                = recoverFromSomeDefects(divBoom)(_ => Some(theNumberOfTheBeast))
      assertM(reco)(equalTo(theNumberOfTheBeast))
    }
  }
}

object Exercise02Test extends DefaultRunnableSpec {
  def spec = suite("Ch 4 => Ex 2")(Exercise02Suite.suiteChp5Ex2)
}
