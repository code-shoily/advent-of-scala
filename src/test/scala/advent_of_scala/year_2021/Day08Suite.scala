package advent_of_scala.year_2021

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2021.Day08

class Day08Suite extends munit.FunSuite:
    val year = 2021
    val day = 8
    val result: (Int, Int) = (534, 1_070_188)

    test("Day 8 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day08(rawInput).solve

        assertEquals(solution, result)
    }
end Day08Suite
