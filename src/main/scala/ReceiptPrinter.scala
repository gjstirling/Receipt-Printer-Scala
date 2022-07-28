/**
 * This method should return a multiline string
 * representing a ReceiptPrinter receipt that should show
 * - the date and time the receipt was created
 * - each item in the order, with the price. eg:
 *     2 x Blueberry Muffin       8.10
 *     1 x Cappuccino             3.85
 * - the total price
 * - the VAT (20% of total price)
 */

import java.time.{Clock, Instant, ZoneId}
import java.time.format.DateTimeFormatter
import scala.math.Equiv

class CafeDetails (
                    val shopName: String,
                    val address: String,
                    val phone: String,
                    val prices: Map[String, Double]
                  )

class ReceiptPrinter(val cafe: CafeDetails, var order: Map[String, Int] = Map(), val clock: Clock = Clock.systemUTC()) {

  def receipt: String = {
    var orderDetails = stringifyOrder(order)
    var totalPrice = orderTotal(order)
    var date = getDateTime()
      s"""${cafe.shopName}, ${cafe.address}, ${cafe.phone}
       |${date}
       |${orderDetails}
       |Total: ${totalPrice}
       |""".stripMargin
  }

  private[this] def getDateTime(): String = {
    val timeNow = Instant.now(clock)
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault())
    val formattedDate = formatter.format(timeNow)
    formattedDate
  }

  private[this] def stringifyOrder(order: Map[String, Int]): String = {
    var stringOrder = order map {case (key, quantity) => (
      s"${quantity} x ${key}     ${calculatePrice(cafe.prices(key), quantity)}"
    )}
    stringOrder.mkString("\n")
  }

  private[this] def calculatePrice(price: Double, quantity: Int): String ={
    val formatter = java.text.NumberFormat.getCurrencyInstance
    val calculatePrice = price*100*quantity/100
    formatter.format(calculatePrice).substring(1)
  }

  private[this] def orderTotal(order: Map[String, Int]): Double ={
    var itemsCost = order map {case (item, quantity) => (
      cafe.prices(item)*quantity
    )}
    var result = itemsCost.reduce(_ + _)
    var total = calculatePrice(result, 1)
    total.toDouble
  }
}
