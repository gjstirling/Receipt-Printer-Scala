import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import java.time.{Clock, Instant, ZoneId}

class ReceiptPrinterSpec extends AnyWordSpec with Matchers {
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

  "A ReceiptPrinter" should {
    "format a receipt" which {
      "contains the name, address and phone number of the cafe" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          Map("Cafe Latte" -> 1)
        )
        printer.receipt should include ("The Coffee Connection, 123 Lakeside Way, 16503600708")
      }

      "Contains the date/time it was created" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          Map("Cafe Latte" -> 1),
          Clock.fixed(Instant.parse("2022-07-27T10:50:00.00Z"), ZoneId.systemDefault())
        )
        printer.receipt should include ("27/07/2022 11:50")
      }

      "Can return a single item ordered" in {
        val order = Map(
          "Flat White" -> 1
        )
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe, order
        )
        printer.receipt should include ("1 x Flat White     4.75")
      }

      "Can return a multiple different items" in {
        val order = Map(
          "Flat White" -> 2,
          "Muffin Of The Day" -> 1
        )
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe, order
        )
        printer.receipt should include ("2 x Flat White     9.50")
        printer.receipt should include ("1 x Muffin Of The Day     4.55")
      }

      "Can calculate the total cost of the order" in {
        val order = Map(
          "Flat White" -> 2,
          "Muffin Of The Day" -> 1
        )
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe, order
        )
        printer.receipt should include ("2 x Flat White     9.50")
        printer.receipt should include ("1 x Muffin Of The Day     4.55")
        printer.receipt should include ("Total: 14.05")
      }

      "Can calculate and display the VAT" in {
        val order = Map(
          "Flat White" -> 2,
          "Muffin Of The Day" -> 1
        )
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe, order
        )
        printer.receipt should include ("VAT: 2.81")
      }
    }

  }
}


