import java.time.{Instant, ZoneId}
import java.time.format.DateTimeFormatter

// src/main/scala/ReceiptPrinter.scala
case class CafeDetails(shopName: String, address: String, phone: String, prices: Map[String, Double])

class ReceiptPrinter(val cafe: CafeDetails, var order: Map[String, Int] = Map(), date: Instant = Instant.now()) {

  /**
   * This method should return a multiline string
   * representing a ReceiptPrinter receipt that should show
   * - shop name, address, phone number
   * - the date and time the receipt was created
   * - each item in the order, with the price. eg:
   *     2 x Blueberry Muffin       8.10
   *     1 x Cappuccino             3.85
   * - the total price
   * - the VAT (20% of total price)
   */
  val formattedDate: String = {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm").withZone(ZoneId.systemDefault())
    dateTimeFormatter.format(date)
  }


  def receipt: String = {
    val result =
      s"""${cafe.shopName} | ${cafe.address} | ${cafe.phone}
                           |${formattedDate}""".stripMargin

    println(Console.MAGENTA + result + Console.RESET)
    result
  }

}
