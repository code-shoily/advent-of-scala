package advent_of_scala.year_2016

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2016.Day19

class Day19Suite extends munit.FunSuite:
    val year = 2016
    val day = 19
    val result = (1_842_613, 1_424_135)

    test("Day 19 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day19(rawInput).solve

        assertEquals(solution, result)
    }
end Day19Suite
