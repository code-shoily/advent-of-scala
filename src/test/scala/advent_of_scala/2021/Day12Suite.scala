package advent_of_scala.year_2021

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2021.Day12

class Day12Suite extends munit.FunSuite:
    val year = 2021
    val day = 12
    val result = (4659, 148_962)

    test("Day 12 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day12(rawInput).solve

        assertEquals(solution, result)
    }
end Day12Suite
