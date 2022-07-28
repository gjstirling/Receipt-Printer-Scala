import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.{Clock, Instant, ZoneId};

class InstantFactoryTest extends AnyWordSpec with Matchers {
  "A InstantFactory" should {
    "create a new date" which {
      "is an instant" in {
        InstantFactory.create() shouldBe a[Instant]
      }
      "has the specified time" in {
        val mockTime = "2022-07-27T14:35:00Z"
        val fixedClock = Clock.fixed(Instant.parse(mockTime), ZoneId.systemDefault())
        val subject = InstantFactory.create(fixedClock)
        subject shouldBe a[Instant]
        subject.toString() shouldBe mockTime
      }
    }
  }
}