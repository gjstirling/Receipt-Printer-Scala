import java.time.{Instant, ZoneId}
import java.time.format.DateTimeFormatter

class ReceiptPrinter(val cafe: CafeDetails, var order: Order = new Order(), val total: Double, val vat: Double, var instantFactory: FactoryBase[Instant] = InstantFactory) {

  def receipt: String = {
    val date = formatInstant(instantFactory.create())
    val mapOrderItemsAsLineItem = order.items.map(orderItemsPrinter).mkString("\n")
    s"""${cafe.shopName}, ${cafe.address}, ${cafe.phone}
       |${date}
       |${mapOrderItemsAsLineItem}
       |Total: ${total}
       |VAT: ${vat}
       |""".stripMargin
  }

  val formatInstant = (instant: Instant) => {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").withZone(ZoneId.systemDefault())
    dateTimeFormatter.format(instant)
  }

  val orderItemsPrinter = (orderItem: OrderItem) => {
    f"${orderItem.quantity}%-2s x ${orderItem.name}%-14s ${orderItem.totalPrice}%10.2f"
  }

}