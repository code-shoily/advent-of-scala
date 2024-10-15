package advent_of_scala.year_2022

import advent_of_scala.utils.IO.readLines
import advent_of_scala.year_2022.Day05

class Day05Suite extends munit.FunSuite:
    val year = 2022
    val day = 5
    val result = ("VPCDMSLWJ", "TPWCGNCCG")

    test("Day 5 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day05(rawInput).solve

        assertEquals(solution, result)
    }
end Day05Suite
