package advent_of_scala.year_2022

import advent_of_scala.base.*

class Solver2022(input: List[String]) extends Solver:
    override def solveFor(day: Int): Solution =
        day match
            case 1 => Day01(input).solve
            case 2 => Day02(input).solve
            case 3 => Day03(input).solve
            case 4 => Day04(input).solve
            case 6 => Day06(input).solve
            case _ => didNotSolveError(2022, day)
end Solver2022
