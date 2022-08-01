import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory

class TillTest extends AnyWordSpec with Matchers with MockFactory {
  "A Till" should {
    "Calculate the subtotal of an order" which {
      "sums up all the order item subtotals" in {
        val mockOrder = new Order(List(new OrderItem("Flat White", 1, 4.75), new OrderItem("Cafe Latte", 1, 4.75)))
        val subject = new Till(mockOrder)

        subject.calculateSubtotal shouldBe 9.50
      }
    }

    "Calculate the VAT of an order" which {
      "VAT" in {
        val mockOrder = new Order(List(new OrderItem("Flat White", 1, 4.75), new OrderItem("Cafe Latte", 1, 4.75)))
        val subject = new Till(mockOrder)

        subject.calculateVAT shouldBe 1.90
      }
    }
  }
}
