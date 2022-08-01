class OrderItem (
                  val name: String,
                  val quantity: Int,
                  val totalPrice: Double
                )

class Order (
            var items: List[OrderItem] = List[OrderItem](),
            )
