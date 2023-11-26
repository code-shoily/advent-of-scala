package advent_of_scala.year_2017

import advent_of_scala.base.*

class Solver2017(input: List[String]) extends Solver:
    override def solveFor(day: Int): Solution =
        day match
            case 1  => Day01(input).solve
            case 2  => Day02(input).solve
            case 19 => Day19(input).solve
            case 20 => Day20(input).solve
            case 21 => Day21(input).solve
            case 22 => Day22(input).solve
            case 23 => Day23(input).solve
            case _  => didNotSolveError(2017, day)
end Solver2017
