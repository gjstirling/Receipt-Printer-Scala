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
