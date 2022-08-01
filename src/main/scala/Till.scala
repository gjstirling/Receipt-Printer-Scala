class Till (val cafe: CafeDetails, val order: Order = new Order()) {

  def calculateSubtotal = {
    order.items.foldLeft(0.0) { (total, orderItem) => total + orderItem.totalPrice }
  }

  def calculateVAT = {
    ((this.calculateSubtotal * 100).toInt * 0.2)/100
  }

  def calculateTotal: Double = {
    this.calculateSubtotal + this.calculateVAT
  }

  def getOrderItem(itemName: String, quantity: Int) = {
    cafe.prices.get(itemName) match {
      case Some(pricePerUnit) => new OrderItem(itemName, quantity, pricePerUnit * quantity)
      case None => throw new IllegalArgumentException(
        "That item doesn't exist in menu!"
      )
    }
  }

  def addItemToOrder(itemName: String, quantity: Int) =
    order.items = order.items ++ List(getOrderItem(itemName, quantity))

}

// Mocking of instant factory
//        val mockInstantFactory = mock[FactoryBase[Instant]]
//        val mockDate = Instant.parse("2022-07-28T14:35:00.00Z")
//
//        (mockInstantFactory.create _).expects().returning(mockDate)
