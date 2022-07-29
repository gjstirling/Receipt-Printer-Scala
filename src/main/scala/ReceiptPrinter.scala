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
import java.time.{Instant, ZoneId}
import java.time.format.DateTimeFormatter

class CafeDetails (
                    val shopName: String,
                    val address: String,
                    val phone: String,
                    val prices: Map[String, Double]
                  )

class ReceiptPrinter(val cafe: CafeDetails, var order: Map[String, Int] = Map(), var instantFactory: FactoryBase[Instant] = InstantFactory) {

  def receipt: String = {
    val findPriceOfItems = order.map(mapOrderItemWithPrice)
    val mapOrderItemsAndPriceAsLine = findPriceOfItems.map(orderPrinter).mkString("\n")
    cafe.shopName + cafe.address + cafe.phone + formatInstant(instantFactory.create()) + mapOrderItemsAndPriceAsLine
  }

  val formatInstant = (instant: Instant) => {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault())
    dateTimeFormatter.format(instant)
  }

  def mapOrderItemWithPrice(orderItem: (String, Int)): (String, Int, Double) = {
    val (name, quantity) = orderItem

    var subTotal =  cafe.prices(name) * quantity
    (name, quantity, subTotal)
  }

  val orderPrinter = (orderItem: (String, Int, Double)) => {
    val (name, quantity, price) = orderItem

    f"${quantity}%-2s x ${name}%-14s ${price}%10.2f"
  }

}