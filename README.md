# TDD - Receipt Printer and Till classes: 

## About
This is a Scala project for a Receipt Printer and Till class.

### Prerequisites

- [Git](https://git-scm.com/)
- [SBT](https://www.scala-sbt.org/)

### Installation

1. **Clone the Repository:**
```git clone https://github.com/gjstirling/Receipt-Printer-Scala```

2. **Navigate to the Project Directory:**
```cd receipt-printer```

## Usage

### Build the Project

To compile the project, run:
```sbt compile```

### Run Tests

To execute tests, run:
```sbt test```

### Generate JAR File

To create a JAR file, run: 
```sbt assembly```

## Directory Structure
- Source code is in the `src` directory.
- Compiled classes are in `target/scala-2.13/classes`.
- Test reports can be found in `target/test-reports`.
- The JAR file (if generated) will be in `target/scala-2.13`.

## Dependencies
- ScalaTest: 3.1.0
- ScalaMock: 5.1.0

## Specification
For this challenge you'll have to:
[ ] Create a new Scala project, adding a testing library as a dependency
[ ] Create a ReceiptPrinter class (see skeleton code below)
[ ] Use TDD to write the code of the receipt method
[ ] Create private methods to extract some logic from the receipt method

**stretch**
Use TDD to create a new class called Till which takes a CafeDetail instance at initialisation.
It should have methods that
[ ] Show the menu
[ ] Allow the user to add an item to their order or throw an error if what they enter is not on the menu
[ ] Finalise the order and print the statement (by calling on the receipt printer)

Use the resources provided and your own research to get to a working solution.

Here are some skeleton files:
```scala
// src/main/scala/ReceiptPrinter.scala
class CafeDetails (
  val shopName: String,
  val address: String,
  val phone: String,
  val prices: Map[String, Double]
)

class ReceiptPrinter(val cafe: CafeDetails, var order: Map[String, Int] = Map()) {

  <!-- OMITTED -->
  def receipt: String = {
    cafe.shopName
  }
}
```

```scala
// src/test/scala/ReceiptPrinterTest.scala
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ReceiptPrinterSpec extends AnyWordSpec with Matchers {
  val coffeeConnectionCafe = new CafeDetails(
    "The Coffee Connection",
    "123 Lakeside Way",
    "16503600708",
    Map(
      "Cafe Latte" -> 4.75,
      "Flat White" -> 4.75,
      "Cappuccino" -> 3.85,
      "Single Espresso" -> 2.05,
      "Double Espresso" -> 3.75,
      "Americano" -> 3.75,
      "Cortado" -> 4.55,
      "Tea" -> 3.65,
      "Choc Mudcake" -> 6.40,
      "Choc Mousse" -> 8.20,
      "Affogato" -> 14.80,
      "Tiramisu" -> 11.40,
      "Blueberry Muffin" -> 4.05,
      "Chocolate Chip Muffin" -> 4.05,
      "Muffin Of The Day" -> 4.55
    )
  )

  "A ReceiptPrinter" should {
    "format a receipt" which {
      "contains the name of the cafe" in {
        val printer = new ReceiptPrinter(
          coffeeConnectionCafe,
          Map("Cafe Latte" -> 1)
        )
        printer.receipt should include ("The Coffee Connection")
      }
      // add more tests here.
    }
  }
}
```

**Note:** You'll notice the style of testing is slightly different in these files. In the last step we have used the `funsuite` style, but as a developer, you'll have to adapt to the style that you team is using. Here we chose the`AnyWordSpec` style, which might be closer to what you'll use on the job.

## Resources:

* [A build.sbt reference file](https://github.com/scala/hello-world.g8/blob/main/src/main/g8/build.sbt)
* [Scala classes](https://docs.scala-lang.org/tour/classes.html)
* [Overview of testing styles in scalatest](https://www.scalatest.org/user_guide/selecting_a_style).
* [Using should matchers](https://www.scalatest.org/user_guide/using_matchers)
* [Using the Map data structure](https://docs.scala-lang.org/overviews/collections/maps.html#operations-in-class-map) (this is a structure similar to ruby hashes)
* [Using mocks in scalatest](https://www.scalatest.org/user_guide/testing_with_mock_objects)


<!-- BEGIN GENERATED SECTION DO NOT EDIT -->

---

**How was this resource?**  
[😫](https://airtable.com/shrUJ3t7KLMqVRFKR?prefill_Repository=makersacademy%2Fintro-to-scala&prefill_File=02_receipt_printer.md&prefill_Sentiment=😫) [😕](https://airtable.com/shrUJ3t7KLMqVRFKR?prefill_Repository=makersacademy%2Fintro-to-scala&prefill_File=02_receipt_printer.md&prefill_Sentiment=😕) [😐](https://airtable.com/shrUJ3t7KLMqVRFKR?prefill_Repository=makersacademy%2Fintro-to-scala&prefill_File=02_receipt_printer.md&prefill_Sentiment=😐) [🙂](https://airtable.com/shrUJ3t7KLMqVRFKR?prefill_Repository=makersacademy%2Fintro-to-scala&prefill_File=02_receipt_printer.md&prefill_Sentiment=🙂) [😀](https://airtable.com/shrUJ3t7KLMqVRFKR?prefill_Repository=makersacademy%2Fintro-to-scala&prefill_File=02_receipt_printer.md&prefill_Sentiment=😀)  
Click an emoji to tell us.

<!-- END GENERATED SECTION DO NOT EDIT -->

### Learning 
- Ensure that the mocked behavior is relevant to the behavior being tested in the current test case. Mocks should mimic the interactions expected during the execution of the tested behavior.
- Double-check the setup of mock expectations to ensure they match the actual behavior expected during the test. Incorrect mock setup can lead to false negatives or positives in test outcomes.
- Each test should be isolated and independent of others. Avoid coupling between test cases, and ensure that each test case can run independently without relying on the state or behavior of other tests.
- Write test cases that are clear, readable, and maintainable. Well-structured tests with descriptive names and clear assertions help in understanding the test intent and debugging failures.

### Further work
- create an interface to run this from the command line
- create an interface to interact from a browser
