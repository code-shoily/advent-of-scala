/** 2021/6: Lanternfish
  *
  * Link: https://adventofcode.com/2021/day/6
  *
  * Difficulty: m
  *
  * Tags: dynamic-programming bignum
  *
  * Answers: (350_149, 1_590_327_954_513)
  */
package advent_of_scala.year_2021

import advent_of_scala.base.Solution
import advent_of_scala.year_2021.Day06.*

class Day06(rawInput: List[String]):
    def solve: Solution =
        val input = parsedInput
        (simulate(input, 80), simulate(input, 256))
    end solve

    private def parsedInput = rawInput.head.split(",").map(_.toInt).toSeq
end Day06

object Day06:
    private def simulate(input: Seq[Int], days: Int): Long =
        (0 until days).foldLeft(
          Vector.tabulate(9)(i => input.count(_ == i).toLong)
        )((fish, day) =>
            val (to, from) = ((day + 7) % 9, day % 9)
            fish.updated(to, fish(to) + fish(from))
        ).sum
    end simulate
end Day06

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2021_06(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2021, 6) match
        case Some(raw_input) =>
            printSolution(Day06(raw_input).solve)
        case _ => impossibleStateError
end run_2021_06
// */
