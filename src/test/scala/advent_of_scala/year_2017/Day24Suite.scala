package advent_of_scala.year_2017

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2017.Day24

class Day24Suite extends munit.FunSuite:
    val year = 2017
    val day = 24
    val result: (Int, Int) = (1656, 1642)

    test("Day 24 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day24(rawInput).solve

        assertEquals(solution, result)
    }
end Day24Suite
