import java.time.Instant
import java.time.Clock

object InstantFactory extends FactoryBase[Instant] {
  def create(): Instant = {
    InstantFactory.create(Clock.systemUTC())
  }

  def create(clock: Clock) = {
    Instant.now(clock)
  }
}