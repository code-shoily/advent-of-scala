package advent_of_scala.year_2022

import advent_of_scala.utils.IO.readLines

class Day06Suite extends munit.FunSuite:
    val year = 2022
    val day = 6
    val result: (Int, Int) = (1651, 3837)

    test("Day 6 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day06(rawInput).solve

        assertEquals(solution, result)
    }
end Day06Suite
