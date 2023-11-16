package advent_of_scala.year_2022

import advent_of_scala.utils.IO.readLines

class Day04Suite extends munit.FunSuite:
    val year = 2022
    val day = 4
    val result = (518, 909)

    test("Day 4 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day04(rawInput).solve

        assertEquals(solution, result)
    }
end Day04Suite
