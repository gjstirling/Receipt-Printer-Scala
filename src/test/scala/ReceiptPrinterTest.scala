import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalamock.scalatest.MockFactory

import java.time.{Instant}

class ReceiptPrinterSpec extends AnyWordSpec with Matchers with MockFactory {
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
      "contains the name of the cafe" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          new Order(List(new OrderItem("Cafe Latte", 1, 4.75)))
        )
        printer.receipt should include ("The Coffee Connection")
      }

      "contains the address of the cafe" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          new Order(List(new OrderItem("Cafe Latte", 1, 4.75)))
        )
        printer.receipt should include ("123 Lakeside Way")
      }

      "contains the phone number of the cafe" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          new Order(List(new OrderItem("Cafe Latte", 1, 4.75)))
        )
        printer.receipt should include ("16503600708")
      }

      "contains the date and time it was printed" in {
        val mockInstantFactory = mock[FactoryBase[Instant]]
        val mockDate = Instant.parse("2022-07-28T14:35:00.00Z")

        (mockInstantFactory.create _).expects().returning(mockDate)

        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          new Order(List(new OrderItem("Cafe Latte", 1, 4.75))),
          14.25,
          3.00,
          mockInstantFactory
        )
        printer.receipt should include ("28/07/2022 15:35")
      }


      "Displays the items ordered" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          new Order(List(new OrderItem("Cafe Latte", 1, 4.75), new OrderItem("Flat White", 2, 9.50)))
        )
        printer.receipt should include ("1  x Cafe Latte           4.75")
        printer.receipt should include ("2  x Flat White           9.50")
      }

      "Displays order subTotal" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          new Order(List(new OrderItem("Cafe Latte", 1, 4.75), new OrderItem("Flat White", 2, 9.50))),
          14.25,
          2.00
        )
        printer.receipt should include ("Total: 14.25")
      }

      "Displays order VAT" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          new Order(List(new OrderItem("Cafe Latte", 1, 4.75), new OrderItem("Flat White", 2, 9.50))),
          14.25,
          2.55
        )
        printer.receipt should include ("VAT: 2.55")
      }

//      "Formats into receipt style format" in {
//        val mockInstantFactory = mock[FactoryBase[Instant]]
//        val mockDate = Instant.parse("2022-07-28T14:35:00.00Z")
//
//        (mockInstantFactory.create _).expects().returning(mockDate)
//        val printer = new ReceiptPrinter(
//          coffeeConnectionCafe,
//          new Order(List(new OrderItem("Cafe Latte", 1, 4.75), new OrderItem("Flat White", 2, 4.75)), 14.25, 2.85),
//          mockInstantFactory
//        )
//        printer.receipt should include (
//          """The Coffee Connection, 123 Lakeside Way, 16503600708
//            |28/07/2022 15:35
//            |1  x Cafe Latte           4.75
//            |2  x Flat White           9.50
//            |Total: 14.25
//            |VAT: 2.85
//            |""".stripMargin
//        )
//      }
    }
  }
}