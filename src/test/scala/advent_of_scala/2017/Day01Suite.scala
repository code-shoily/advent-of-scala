package advent_of_scala.tests.year_2017

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2017.Day01

class Day01Suite extends munit.FunSuite:
    val year = 2017
    val day = 1
    val result = (1089, 1156)

    test("Day 1 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day01(rawInput).solve

        assertEquals(solution, result)
    }
end Day01Suite
