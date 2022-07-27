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

import java.util.Calendar

class CafeDetails (
                    val shopName: String,
                    val address: String,
                    val phone: String,
                    val prices: Map[String, Double]
                  )

class ReceiptPrinter(val cafe: CafeDetails, var order: Map[String, Int] = Map()) {

  def receipt: String = {
    var orderDetails = stringifyOrder(order)
    var date = getDateTime()
      s"""${cafe.shopName}, ${cafe.address}, ${cafe.phone}
       |${date}
       |${orderDetails}
       |""".stripMargin
  }

  def getDateTime(): String = {
    var now = Calendar.getInstance()
    var date = s"${now.get(Calendar.DAY_OF_MONTH)}/${now.get(Calendar.MONTH)+1}/${now.get(Calendar.YEAR)}"
    var time = s"${now.get(Calendar.HOUR_OF_DAY)}:${now.get(Calendar.MINUTE)}"
    s"${date} ${time}"
  }

  private[this] def stringifyOrder(order: Map[String, Int]): String = {
    var stringOrder = order map {case (key, quantity) => (
      s"${quantity} x ${key}     ${calculateTotalPrice(cafe.prices(key), quantity)}"
    )}
    stringOrder.mkString("\n")
  }

  def calculateTotalPrice(price: Double, quantity: Int): String ={
    val formatter = java.text.NumberFormat.getCurrencyInstance
    val calculatePrice = price*100*quantity/100
    formatter.format(calculatePrice).substring(1)
  }
}
