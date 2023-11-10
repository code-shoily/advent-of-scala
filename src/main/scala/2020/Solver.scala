package advent_of_scala.year_2020

import advent_of_scala.base.*

class Solver2020(input: List[String]) extends Solver:
  override def solveFor(day: Int): Solution =
    day match
      case 1 => Day01(input).solve()
      case _ => sys.error(s"Did not solve day 2020/$day yet")
