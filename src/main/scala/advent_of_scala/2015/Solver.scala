package advent_of_scala.year_2015

import advent_of_scala.base.*

class Solver2015(input: List[String]) extends Solver:
    override def solveFor(day: Int): Solution =
        day match
            case 1 => Day01(input).solve
            case _ => didNotSolveError(2015, day)
end Solver2015
