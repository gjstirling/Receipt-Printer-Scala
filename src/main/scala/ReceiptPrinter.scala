
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
    val mapOrderItemsAndPriceAsLine = findPriceOfItems.map(orderPrinter).mkString("")
    val calculateSubTotal = findPriceOfItems.foldLeft(0.0) { (total, orderItem) => total + orderItem._3 }
    val subTotal = s"Total: ${calculateSubTotal}"
    val vat = s"VAT: ${calculateSubTotal*0.2}"
    cafe.shopName + cafe.address + cafe.phone + formatInstant(instantFactory.create()) + mapOrderItemsAndPriceAsLine + subTotal + vat
  }

  val formatInstant = (instant: Instant) => {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault())
    dateTimeFormatter.format(instant)
  }

  val orderPrinter = (orderItem: (String, Int, Double)) => {
    val (name, quantity, price) = orderItem

    f"${quantity}%-2s x ${name}%-14s ${price}%10.2f"
  }

  private[this] def mapOrderItemWithPrice(orderItem: (String, Int)): (String, Int, Double) = {
    val (name, quantity) = orderItem

    var subTotal =  cafe.prices(name) * quantity
    (name, quantity, subTotal)
  }
}