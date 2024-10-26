package advent_of_scala.year_2017

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2017.Day20

class Day20Suite extends munit.FunSuite:
    val year = 2017
    val day = 20
    val result: (Int, Int) = (300, 502)

    test("Day 20 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day20(rawInput).solve

        assertEquals(solution, result)
    }
end Day20Suite
