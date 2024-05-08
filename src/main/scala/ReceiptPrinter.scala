import ReceiptPrinter.{DateFormat, VatRate}
import java.time.{Instant, ZoneId}
import java.time.format.DateTimeFormatter

case class CafeDetails(shopName: String, address: String, phone: String, prices: Map[String, Double])

object ReceiptPrinter {
  private val DateFormat = "MM/dd/yyyy HH:mm"
  private val VatRate = 0.2
}

case class ReceiptPrinter(cafe: CafeDetails, order: Map[String, Int] = Map(), date: Instant = Instant.now()) {

  /**
   * This method should return a multiline string
   * representing a ReceiptPrinter receipt that should show
   * - shop name, address, phone number
   * - the date and time the receipt was created
   * - each item in the order, with the price. eg:
   * 2 x Blueberry Muffin       8.10
   * 1 x Cappuccino             3.85
   * - the total price
   * - the VAT (20% of total price)
   */
  private val formattedDate: String = {
    DateTimeFormatter
      .ofPattern(DateFormat)
      .withZone(ZoneId.systemDefault())
      .format(date)
  }

  private def calculatePrice(item: String, quantity: Int): Double = {
    cafe.prices(item) * quantity
  }

  private def formatOrderItems(order: Map[String, Int]): String = {
    formatOrder(order).mkString("\n")
  }

  private val formatOrder: Map[String, Int] => List[String] = (order: Map[String, Int]) => {
    order.map { item => {
      val price = calculatePrice(item._1, item._2)

      s"""${item._2} x ${item._1} ${f"$price%.2f"}"""
    }
    }.toList
  }

  private val subtotal: Double = order.map(item => calculatePrice(item._1, item._2)).sum

  private def formatShopDetails: String = {
    s"${cafe.shopName} | ${cafe.address} | ${cafe.phone}"
  }

  private def formatTotalPrice: String = {
    s"Total Price: ${f"$subtotal%.2f"}"
  }

  private def formatVAT: String = {
    s"VAT: ${f"${subtotal * VatRate}%.2f"}"
  }

  def receipt: String = {
    s"""${formatShopDetails}
       |${formattedDate}
       |${formatOrderItems(order)}
       |${formatTotalPrice}
       |${formatVAT}
       """.stripMargin
  }

}
