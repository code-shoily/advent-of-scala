package advent_of_scala.year_2016

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2016.Day11

class Day11Suite extends munit.FunSuite:
    val year = 2016
    val day = 11
    val result = (37, 61)

    test("Day 11 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day11(rawInput).solve

        assertEquals(solution, result)
    }
end Day11Suite
