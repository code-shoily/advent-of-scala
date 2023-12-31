package advent_of_scala.year_2016

import advent_of_scala.base.*

class Solver2016(input: List[String]) extends Solver:
    override def solveFor(day: Int): Solution =
        day match
            case 1  => Day01(input).solve
            case 2  => Day02(input).solve
            case 3  => Day03(input).solve
            case 14 => Day14(input).solve
            case 17 => Day17(input).solve
            case 18 => Day18(input).solve
            case 19 => Day19(input).solve
            case 24 => Day24(input).solve
            case _  => didNotSolveError(2016, day)
end Solver2016
