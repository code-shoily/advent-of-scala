package advent_of_scala.year_2017

import advent_of_scala.utils.IO.readLines

class Day01Suite extends munit.FunSuite:
    val year = 2017
    val day = 1
    val result: (Int, Int) = (1089, 1156)

    test("Day 1 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day01(rawInput).solve

        assertEquals(solution, result)
    }
end Day01Suite
