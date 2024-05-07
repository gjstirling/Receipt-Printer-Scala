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
    DateTimeFormatter
      .ofPattern("MM/dd/yyyy HH:mm")
      .withZone(ZoneId.systemDefault())
      .format(date)
  }

  val formatOrder: Map[String, Int] => List[String] = (order: Map[String, Int]) => {
    order.map { item => {
      val price = cafe.prices(item._1) * item._2

      s"""${item._2} x ${item._1} ${f"$price%.2f"}"""
    }
    }.toList
  }

  val subtotal: Double = order.map(item => {
    cafe.prices(item._1) * item._2
  }).toList.sum


  def receipt: String = {
    val result =
      s"""${cafe.shopName} | ${cafe.address} | ${cafe.phone}
                           |${formattedDate}
                           |${formatOrder(order).mkString("\n")}
                           |total price: ${s"""${f"$subtotal%.2f"}"""}
                           |VAT: ${s"""${f"${subtotal*0.2}%.2f"}"""}
       """.stripMargin

    println(Console.MAGENTA + result + Console.RESET)
    result
  }

}
