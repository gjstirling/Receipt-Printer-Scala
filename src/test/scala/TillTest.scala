import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory

class TillTest extends AnyWordSpec with Matchers with MockFactory {
  val coffeeConnectionCafe = new CafeDetails(
    "The Coffee Connection",
    "123 Lakeside Way",
    "16503600708",
    Map(
      "Cafe Latte" -> 4.75,
      "Flat White" -> 4.75,
      "Cappuccino" -> 3.85,
      "Single Espresso" -> 2.05,
      "Double Espresso" -> 3.75,
      "Americano" -> 3.75,
      "Cortado" -> 4.55,
      "Tea" -> 3.65,
      "Choc Mudcake" -> 6.40,
      "Choc Mousse" -> 8.20,
      "Affogato" -> 14.80,
      "Tiramisu" -> 11.40,
      "Blueberry Muffin" -> 4.05,
      "Chocolate Chip Muffin" -> 4.05,
      "Muffin Of The Day" -> 4.55
    )
  )

  "A Till" should {
    "Calculate the subtotal of an order" which {
      "sums up all the order item subtotals" in {
        val mockOrder = new Order(List(new OrderItem("Flat White", 1, 4.75), new OrderItem("Cafe Latte", 1, 4.75)))
        val subject = new Till(
          coffeeConnectionCafe,
          mockOrder
        )

        subject.calculateSubtotal shouldBe 9.50
      }
    }

    "Calculate the VAT of an order" which {
      "VAT" in {
        val mockOrder = new Order(List(new OrderItem("Flat White", 1, 4.75), new OrderItem("Cafe Latte", 1, 4.75)))
        val subject = new Till(
          coffeeConnectionCafe,
          mockOrder
        )
        subject.calculateVAT shouldBe 1.90
      }
    }

    "Calculate the total cost of the order" which {
      "Sums the subTotal and VAT" in {
        val mockOrder = new Order(List(new OrderItem("Flat White", 1, 4.75), new OrderItem("Cafe Latte", 1, 4.75)))
        val subject = new Till(
          coffeeConnectionCafe,
          mockOrder
        )
        subject.calculateTotal shouldBe 11.40
      }
    }

    "Add an item to the order" which {
      "Has the total price (pricePerUnit x quantity)" in {
        val subject = new Till(coffeeConnectionCafe)

        subject.addItemToOrder("Cafe Latte", 5)

        subject.order.items.size shouldBe 1
        subject.order.items(0) shouldBe a [OrderItem]
        subject.order.items(0).name shouldBe "Cafe Latte"
        subject.order.items(0).quantity shouldBe 5
        subject.order.items(0).totalPrice shouldBe 23.75
      }
    }
  }
}
