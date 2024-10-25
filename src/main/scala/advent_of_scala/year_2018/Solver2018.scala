package advent_of_scala.year_2018

import advent_of_scala.base.*

class Solver2018(input: List[String]) extends Solver:
    override def solveFor(day: Int): Solution =
        day match
            case 1 => Day01(input).solve
            case 2 => Day02(input).solve
            case _ => didNotSolveError(2018, day)
end Solver2018
