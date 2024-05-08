
class Till(val cafeDetails: CafeDetails, order: Map[String, Int] = Map.empty) {

  def printMenu: String = {
   cafeDetails.prices
      .map(item => f"${item._1}%20s | ${item._2}%5.2f")
      .toList
      .mkString("\n")
  }

  def addToOrder(itemName: String, quantity: Int = 1): Boolean = {
    cafeDetails.prices.get(itemName) match {
      case Some(price) =>
        val updatedOrder = order + (itemName -> (order.getOrElse(itemName, 0) + quantity))
        println(s"$quantity $itemName added to the order. Current total: ${updatedOrder(itemName)}")
        true
      case None =>
        println(s"[Till][addToOrder] ERROR: $itemName not found")
        false
    }
  }



}