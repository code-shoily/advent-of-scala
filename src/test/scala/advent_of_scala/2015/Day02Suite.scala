package advent_of_scala.tests.year_2015

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2015.Day02

class Day02Suite extends munit.FunSuite:
    val year = 2015
    val day = 2
    val result = (1_606_483, 3_842_356)

    test("Day 2 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day02(rawInput).solve

        assertEquals(solution, result)
    }
end Day02Suite
