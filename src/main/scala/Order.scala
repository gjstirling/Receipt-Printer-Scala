class OrderItem (
                  val name: String,
                  val quantity: Int,
                  val totalPrice: Double
                )

class Order (
            val items: List[OrderItem] = List[OrderItem](),
            val totalPrice: Double = 0,
            val VAT: Double = 0
            )

//val findPriceOfItems = order.map(mapOrderItemWithPrice)
//val calculateSubTotal = findPriceOfItems.foldLeft(0.0) { (total, orderItem) => total + orderItem._3 }
//val subTotal = s"Total: ${calculateSubTotal}"
//val vat = s"VAT: ${calculateSubTotal*0.2}"

//extract from Receipt printer
//    val findPriceOfItems = order.map(mapOrderItemWithPrice)
//    val mapOrderItemsAndPriceAsLine = findPriceOfItems.map(orderPrinter).mkString("\n")
//    val calculateSubTotal = findPriceOfItems.foldLeft(0.0) { (total, orderItem) => total + orderItem._3 }
//    val subTotal = s"Total: ${calculateSubTotal}"
//    val vat = s"VAT: ${calculateSubTotal*0.2}"

// move to order class
//  private[this] def mapOrderItemWithPrice(orderItem: (String, Int)): (String, Int, Double) = {
//    val (name, quantity) = orderItem
//
//    var subTotal =  cafe.prices(name) * quantity
//    (name, quantity, subTotal)
//  }
