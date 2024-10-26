package advent_of_scala.year_2016

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2016.Day03

class Day03Suite extends munit.FunSuite:
    val year = 2016
    val day = 3
    val result: (Int, Int) = (993, 1849)

    test("Day 3 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day03(rawInput).solve

        assertEquals(solution, result)
    }
end Day03Suite
