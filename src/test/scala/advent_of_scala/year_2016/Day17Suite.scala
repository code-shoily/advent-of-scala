package advent_of_scala.year_2016

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2016.Day17

class Day17Suite extends munit.FunSuite:
    val year = 2016
    val day = 17
    val result: (String, Int) = ("DURLDRRDRD", 650)

    test("Day 17 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day17(rawInput).solve

        assertEquals(solution, result)
    }
end Day17Suite
