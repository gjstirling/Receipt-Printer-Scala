import java.time.{Clock, Instant, ZoneId}

class CafeDetails (
                    val shopName: String,
                    val address: String,
                    val phone: String,
                    val prices: Map[String, Double]
                  )

class ReceiptPrinter(val cafe: CafeDetails, var order: Map[String, Int] = Map(), val clock: Clock = Clock.systemUTC()) {

  def receipt: String = {
      s"""${cafe.shopName}, ${cafe.address}, ${cafe.phone}
       |""".stripMargin
  }
}
