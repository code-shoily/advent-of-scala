/** Day 1: Chronal Calibration
  *
  * Link: https://adventofcode.com/2018/day/1
  *
  * Difficulty: xs
  *
  * Tags: linked-list
  *
  * Answers: (590, 83_445)
  */
package advent_of_scala.year_2018

import advent_of_scala.base.Solution
import scala.annotation.tailrec

class Day01(rawInput: List[String]):
    def solvePart1(input: List[Int]): Int = input.sum
    def solvePart2(input: List[Int]): Int =
        val frequencyHistory = Set[Int]()

        @tailrec
        def recurse(
            frequencies: List[Int],
            history: Set[Int],
            total: Int = 0
        ): Int = frequencies match
            case x :: rest if history.contains(x + total) => x + total
            case x :: rest => recurse(rest, history + (x + total), x + total)
            case Nil =>
                recurse(input, history, total)

        recurse(input, frequencyHistory)
    end solvePart2

    def solve: Solution =
        val input = parsedInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    def parsedInput = rawInput.map(_.toInt).toList
end Day01

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2018_01 =
    import advent_of_scala.utils.IO.{readLines, printSolution}

    readLines(2018, 1) match
        case Some(rawInput) =>
            val solver = Day01(rawInput)
            printSolution(solver.solve)
        case _ => sys.error("Could not read file")
    end match
end run_2018_01
// */
