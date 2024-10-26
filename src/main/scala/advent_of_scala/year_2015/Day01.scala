/** Day 1: Not Quite Lisp
  *
  * Link: https://adventofcode.com/2015/day/1
  *
  * Difficulty: xs
  *
  * Tags: sequence reduction
  *
  * Answers: (232, 1783)
  */
package advent_of_scala.year_2015

import advent_of_scala.base.Solution
import advent_of_scala.year_2015.Day01.*

class Day01(rawInput: List[String]):
    def solve: Solution =
        val input = parsedInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    def solvePart1(input: InputType): Int = input.sum

    def solvePart2(input: InputType): Int = input.scan(0)(_ + _).indexOf(-1)

    private def parsedInput: InputType =
        rawInput.head.map((step: Char) => if step == '(' then 1 else -1)
end Day01

object Day01:
    type InputType = Seq[Int]

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2015_01(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2015, 1) match
        case Some(raw_input) =>
            printSolution(Day01(raw_input).solve)
        case _ => impossibleStateError
end run_2015_01
// */
