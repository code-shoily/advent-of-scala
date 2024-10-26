package advent_of_scala.year_2016

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2016.Day18

class Day18Suite extends munit.FunSuite:
    val year = 2016
    val day = 18
    val result: (Int, Int) = (1951, 20_002_936)

    test("Day 18 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day18(rawInput).solve

        assertEquals(solution, result)
    }
end Day18Suite
