package advent_of_scala.year_2020

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2020.Day04

class Day04Suite extends munit.FunSuite:
    val year = 2020
    val day = 4
    val result = (233, 111)

    test("Day 4 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day04(rawInput).solve

        assertEquals(solution, result)
    }
end Day04Suite
