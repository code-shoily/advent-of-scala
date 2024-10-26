package advent_of_scala.year_2016

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2016.Day14

class Day14Suite extends munit.FunSuite:
    val year = 2016
    val day = 14
    val result: (Int, Int) = (15_168, 20_864)

    test("Day 14 solve yields correct result".ignore) {
        val rawInput = readLines(year, day).get
        val solution = Day14(rawInput).solve

        assertEquals(solution, result)
    }
end Day14Suite
