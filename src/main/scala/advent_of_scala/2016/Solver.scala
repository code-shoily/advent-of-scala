package advent_of_scala.year_2016

import advent_of_scala.base.*

class Solver2016(input: List[String]) extends Solver:
    override def solveFor(day: Int): Solution =
        day match
            case 1 => Day01(input).solve
            case 3 => Day03(input).solve
            case _ => didNotSolveError(2016, day)
end Solver2016
