package advent_of_scala.tests.year_2015

import advent_of_scala.utils.io.readLines
import advent_of_scala.year_2015.Day01


class Day01Suite extends munit.FunSuite {
  val year = 2015
  val day = 1
  val result = (232, 1783)

  test("Day 1 solve yields correct result") {
    val rawInput = readLines(year, day).get
    val solution = Day01(rawInput).solve()

    assert(solution == result)
  }
}
