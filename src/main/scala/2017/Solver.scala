package advent_of_scala.year_2017

import advent_of_scala.base.*

class Solver2017(input: List[String]) extends Solver:
  override def solveFor(day: Int): Solution =
    day match
      case 1 => Day01(input).solve()
      case _ => sys.error(s"Did not solve day 2017/$day yet")
