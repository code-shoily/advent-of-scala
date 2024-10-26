package advent_of_scala.year_2015

import advent_of_scala.utils.IO.readLines

class Day04Suite extends munit.FunSuite:
    val year = 2015
    val day = 4
    val result: (Int, Int) = (254_575, 1_038_736)

    test("Day 4 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day04(rawInput).solve

        assertEquals(solution, result)
    }
end Day04Suite
