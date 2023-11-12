package advent_of_scala.tests.year_2019

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2019.Day01

class Day01Suite extends munit.FunSuite:
    val year = 2019
    val day = 1
    val result = (3_421_505, 5_129_386)

    test("Day 1 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day01(rawInput).solve()

        assertEquals(solution, result)
    }
end Day01Suite
