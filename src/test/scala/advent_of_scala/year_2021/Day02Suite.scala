package advent_of_scala.year_2021

import advent_of_scala.utils.IO.readLines

class Day02Suite extends munit.FunSuite:
    val year = 2021
    val day = 2
    val result: (Int, Int) = (1_660_158, 1_604_592_846)

    test("Day 2 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day02(rawInput).solve

        assertEquals(solution, result)
    }
end Day02Suite
