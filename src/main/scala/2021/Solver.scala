package year_2021

import base.*

class Solver2021(input: List[String]) extends Solver:
  override def solveFor(day: Int): Solution =
    day match
      case 1 => Day01(input).solve()
      case _ => sys.error(s"Did not solve day 2021/$day yet")
