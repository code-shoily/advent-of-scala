package advent_of_scala.year_2016

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2016.Day15

class Day15Suite extends munit.FunSuite:
    val year = 2016
    val day = 15
    val result = (317_371, 2_080_951)

    test("Day 15 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day15(rawInput).solve

        assertEquals(solution, result)
    }
end Day15Suite
