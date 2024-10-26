package advent_of_scala.year_2020

import advent_of_scala.base.Solution
import advent_of_scala.utils.IO.readLines

class Day03Suite extends munit.FunSuite:
    val year = 2020
    val day = 3
    val result: Solution = (272L, 3898725600L)

    test("Day 3 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day03(rawInput).solve

        assertEquals(solution, result)
    }
end Day03Suite
