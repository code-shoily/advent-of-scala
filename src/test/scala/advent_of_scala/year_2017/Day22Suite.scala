package advent_of_scala.year_2017

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2017.Day22

class Day22Suite extends munit.FunSuite:
    val year = 2017
    val day = 22
    val result: (Int, Int) = (5447, 2_511_705)

    test("Day 22 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day22(rawInput).solve

        assertEquals(solution, result)
    }
end Day22Suite
