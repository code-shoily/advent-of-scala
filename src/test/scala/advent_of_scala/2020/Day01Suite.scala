package advent_of_scala.tests.year_2020

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2020.Day01

class Day01Suite extends munit.FunSuite:
    val year = 2020
    val day = 1
    val result = (1_014_624, 80_072_256)

    test("Day 1 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day01(rawInput).solve

        assertEquals(solution, result)
    }
end Day01Suite
