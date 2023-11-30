package advent_of_scala.year_2020

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2020.Day09

class Day09Suite extends munit.FunSuite:
    val year = 2020
    val day = 9
    val result = (15_353_384, 2_466_556)

    test("Day 9 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day09(rawInput).solve

        assertEquals(solution, result)
    }
end Day09Suite
