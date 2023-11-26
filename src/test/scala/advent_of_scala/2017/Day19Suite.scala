package advent_of_scala.year_2017

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2017.Day19

class Day19Suite extends munit.FunSuite:
    val year = 2017
    val day = 19
    val result = ("LIWQYKMRP", 16764)

    test("Day 19 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day19(rawInput).solve

        assertEquals(solution, result)
    }
end Day19Suite
