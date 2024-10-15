package advent_of_scala.year_2021

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2021.Day04

class Day04Suite extends munit.FunSuite:
    val year = 2021
    val day = 4
    val result = (11_774, 4495)

    test("Day 4 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day04(rawInput).solve

        assertEquals(solution, result)
    }
end Day04Suite
