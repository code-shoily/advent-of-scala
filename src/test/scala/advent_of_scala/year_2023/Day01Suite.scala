package advent_of_scala.year_2023

import advent_of_scala.utils.IO.readLines

class Day01Suite extends munit.FunSuite:
    val year = 2023
    val day = 1
    val result: (Int, Int) = (53_194, 54_249)

    test("Day 1 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day01(rawInput).solve

        assertEquals(solution, result)
    }
end Day01Suite
