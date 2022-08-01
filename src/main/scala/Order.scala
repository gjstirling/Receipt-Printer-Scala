class OrderItem (
                  val name: String,
                  val quantity: Int,
                  val totalPrice: Double
                )

class Order (
            val items: List[OrderItem] = List[OrderItem](),
            )
