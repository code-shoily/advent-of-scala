package advent_of_scala.year_2015

import advent_of_scala.utils.IO.readLines

class Day03Suite extends munit.FunSuite:
    val year = 2015
    val day = 3
    val result: (Int, Int) = (2081, 2341)

    test("Day 3 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day03(rawInput).solve

        assertEquals(solution, result)
    }
end Day03Suite
