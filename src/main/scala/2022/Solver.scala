package advent_of_scala.year_2022

import advent_of_scala.base.*

class Solver2022(input: List[String]) extends Solver:
  override def solveFor(day: Int): Solution =
    day match
      case 1 => Day01(input).solve()
      case _ => sys.error(didNotSolve(2022, day))
