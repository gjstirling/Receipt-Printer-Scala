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
    s"""${cafe.shopName}, ${cafe.address}, ${cafe.phone}
       |${getDateTime()}
       |""".stripMargin
  }

  def getDateTime(): String = {
    var now = Calendar.getInstance()
    var date = s"${now.get(Calendar.DAY_OF_MONTH)}/${now.get(Calendar.MONTH)+1}/${now.get(Calendar.YEAR)}"
    var time = s"${now.get(Calendar.HOUR_OF_DAY)}:${now.get(Calendar.MINUTE)}"
    s"${date} ${time}"
  }
}