package advent_of_scala.year_2022

import advent_of_scala.utils.IO.readLines

class Day03Suite extends munit.FunSuite:
    val year = 2022
    val day = 3
    val result = (8233, 2821)

    test("Day 3 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day03(rawInput).solve

        assertEquals(solution, result)
    }
end Day03Suite
