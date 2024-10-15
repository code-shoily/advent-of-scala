package advent_of_scala.year_2017

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2017.Day21

class Day21Suite extends munit.FunSuite:
    val year = 2017
    val day = 21
    val result = (133, 2_221_990)

    test("Day 21 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day21(rawInput).solve

        assertEquals(solution, result)
    }
end Day21Suite
