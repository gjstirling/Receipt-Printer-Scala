
class Till(val cafeDetails: CafeDetails) {

  def printMenu: String = {
   cafeDetails.prices
      .map(item => f"${item._1}%20s | ${item._2}%5.2f")
      .toList
      .mkString("\n")
  }

}