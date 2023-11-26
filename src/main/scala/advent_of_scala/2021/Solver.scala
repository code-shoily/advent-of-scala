package advent_of_scala.year_2021

import advent_of_scala.base.*

class Solver2021(input: List[String]) extends Solver:
    override def solveFor(day: Int): Solution =
        day match
            case 1  => Day01(input).solve
            case 2  => Day02(input).solve
            case 3  => Day03(input).solve
            case 4  => Day04(input).solve
            case 5  => Day05(input).solve
            case 6  => Day06(input).solve
            case 8  => Day08(input).solve
            case 12 => Day12(input).solve
            case 13 => Day13(input).solve
            case _  => didNotSolveError(2021, day)
end Solver2021
