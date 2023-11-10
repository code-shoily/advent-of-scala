package advent_of_scala.tests.year_2016

import advent_of_scala.utils.io.readLines
import advent_of_scala.year_2016.Day01

class Day01Suite extends munit.FunSuite {
  val year = 2016
  val day = 1
  val result = (253, 126)

  test("Day 1 solve yields correct result") {
    val rawInput = readLines(year, day).get
    val solution = Day01(rawInput).solve()

    assertEquals(solution, result)
  }
}
