class Till (val order: Order = new Order()) {

  def calculateSubtotal = {
    order.items.foldLeft(0.0) { (total, orderItem) => total + orderItem.totalPrice }
  }

  def calculateVAT = {
    ((this.calculateSubtotal * 100).toInt * 0.2)/100
  }

  def calculateTotal: Double = {
    this.calculateSubtotal + this.calculateVAT
  }

}

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
