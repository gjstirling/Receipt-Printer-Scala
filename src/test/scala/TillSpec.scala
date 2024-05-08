import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TillSpec extends AnyWordSpec with Matchers {

  val coffeeConnectionCafe: CafeDetails = CafeDetails(
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
    "Show the menu" which {
      "contains a flat white" in {
        val till = new Till(coffeeConnectionCafe)
        till.printMenu should include("Flat White |  4.75")
      }

      "contains all items" in {
        val till = new Till(coffeeConnectionCafe).printMenu

        till should include("Cappuccino |  3.85")
        till should include("Chocolate Chip Muffin |  4.05")
        till should include("Muffin Of The Day |  4.55")
      }
    }

    "Add items to an order list" which {
      "can add a flat white" in {
        val till = new Till(coffeeConnectionCafe)

        till.addToOrder("Flat White") shouldBe true
      }

      "can ignore case issues with a flat white" in {
        val till = new Till(coffeeConnectionCafe)

        till.addToOrder("flat white") shouldBe true
        till.addToOrder("FLAT WHITE") shouldBe true
      }
    }
  }

}