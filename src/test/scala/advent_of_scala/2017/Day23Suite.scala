package advent_of_scala.year_2017

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2017.Day23

class Day23Suite extends munit.FunSuite:
    val year = 2017
    val day = 23
    val result = (6241, 909)

    test("Day 23 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day23(rawInput).solve

        assertEquals(solution, result)
    }
end Day23Suite
