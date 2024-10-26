package advent_of_scala.year_2016

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2016.Day24

class Day24Suite extends munit.FunSuite:
    val year = 2016
    val day = 24
    val result: (Int, Int) = (462, 676)

    test("Day 24 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day24(rawInput).solve

        assertEquals(solution, result)
    }
end Day24Suite
