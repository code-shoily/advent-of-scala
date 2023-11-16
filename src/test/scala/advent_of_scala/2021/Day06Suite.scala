package advent_of_scala.year_2021

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2021.Day06

class Day06Suite extends munit.FunSuite:
    val year = 2021
    val day = 6
    val result = (350_149L, 1_590_327_954_513L)

    test("Day 6 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day06(rawInput).solve

        assertEquals(solution, result)
    }
end Day06Suite
