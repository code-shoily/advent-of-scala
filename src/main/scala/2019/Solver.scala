package advent_of_scala.year_2019

import advent_of_scala.base.*

class Solver2019(input: List[String]) extends Solver:
    override def solveFor(day: Int): Solution =
        day match
            case 1 => Day01(input).solve()
            case _ => sys.error(didNotSolve(2019, day))
end Solver2019
