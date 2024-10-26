package advent_of_scala.year_2021

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2021.Day13

class Day13Suite extends munit.FunSuite:
    val year = 2021
    val day = 13
    val result: (Int, String) = (653, "LKREBPRK")

    test("Day 13 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day13(rawInput).solve

        assertEquals(solution, result)
    }
end Day13Suite
