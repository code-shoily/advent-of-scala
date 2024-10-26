package advent_of_scala.year_2020

import advent_of_scala.utils.IO.readLines

class Day02Suite extends munit.FunSuite:
    val year = 2020
    val day = 2
    val result: (Int, Int) = (607, 321)

    test("Day 2 solve yields correct result") {
        val rawInput = readLines(year, day).get
        val solution = Day02(rawInput).solve

        assertEquals(solution, result)
    }
end Day02Suite
