package swcraft

import definiti.native.{Invalid, Valid}
import org.scalatest.{FlatSpec, Matchers}

class TheaterSpec extends FlatSpec with Matchers {
  import TheaterSpec._

  "Theater validation" should "accept a nominal Theater" in {
    val input = nominal
    val output = Theater.allVerifications.verify(input)
    output should be(Valid(input))
  }

  it should "refuse a theater with an empty name" in {
    val input = nominal.copy(name = "")
    val output = Theater.allVerifications.verify(input)
    output should be(Invalid(Seq("Please provide a valid name")))
  }

  it should "refuse a theater with an empty address" in {
    val input = nominal.copy(address = "")
    val output = Theater.allVerifications.verify(input)
    output should be(Invalid(Seq("Please provide a valid address")))
  }

  it should "refuse a theater without plans" in {
    val input = nominal.copy(plans = List.empty)
    val output = Theater.allVerifications.verify(input)
    output should be(Invalid("Please give at least one plan"))
  }

  it should "refuse a theater with a plan without seats" in {
    val input = nominalWithSeats(List.empty)
    val output = Theater.allVerifications.verify(input)
    output should be(Invalid("The list should not be empty"))
  }

  it should "refuse a theater with several seats with the same name" in {
    val input = nominalWithSeats(List(
      Seat("A1", Position(1, 2)),
      Seat("A1", Position(1, 2))
    ))
    val output = Theater.allVerifications.verify(input)
    output should be(Invalid("Two seats cannot have the same code"))
  }
}

object TheaterSpec {
  val nominal = Theater(
    name = "My theater",
    address = "Somewhere in the world",
    plans = List(
      Plan(
        stage = Position(1, 1),
        seats = List(
          Seat("A1", Position(1, 2)),
          Seat("A2", Position(2, 1)),
          Seat("B1", Position(2, 2))
        )
      )
    )
  )

  def nominalWithSeats(seats: List[Seat]): Theater = {
    Theater(
      name = "My theater",
      address = "Somewhere in the world",
      plans = List(
        Plan(
          stage = Position(1, 1),
          seats = seats
        )
      )
    )
  }
}