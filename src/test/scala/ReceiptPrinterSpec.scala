// src/test/scala/ReceiptPrinterTest.scala
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.{Clock, Instant, ZoneId}

class ReceiptPrinterSpec extends AnyWordSpec with Matchers {
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

  "A ReceiptPrinter" should {
    "format a receipt" which {
      "contains the name of the cafe" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe
        )
        printer.receipt should include ("The Coffee Connection")
      }
      // add more tests here.

      "contain the cafe's address" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe
        )
        printer.receipt should include("123 Lakeside Way")
      }

      "contain the cafe's phone number" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe
        )
        printer.receipt should include("16503600708")
      }

      "include a timestamp" in {
        val mockTime = "2019-01-01T10:35:00Z"
        val fixedClock = Clock.fixed(Instant.parse(mockTime), ZoneId.systemDefault())

        val printer = new ReceiptPrinter(
          cafe = coffeeConnectionCafe,
          date = Instant.now(fixedClock)
        )
        printer.receipt should include("01/01/2019 10:35")
      }

      "display an order item" in {
        val printer = new ReceiptPrinter(
          cafe = coffeeConnectionCafe,
          order = Map("Cappuccino" -> 1)
        )
        printer.receipt should include("1 x Cappuccino 3.85")
      }

      "displays multiple order items" in {
        val printer = new ReceiptPrinter(
          cafe = coffeeConnectionCafe,
          order = Map("Cappuccino" -> 1, "Flat White" -> 2)
        )

        printer.receipt should include("1 x Cappuccino 3.85")
        printer.receipt should include("2 x Flat White 9.50")
      }
    }
  }
}