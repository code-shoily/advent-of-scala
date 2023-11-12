/** Day 1: Not Quite Lisp
  *
  * Link: https://adventofcode.com/2015/day/1
  *
  * Difficulty: xs
  *
  * Tags: sequence reduce
  */
package advent_of_scala.year_2015

import advent_of_scala.base.{Solution, impossibleStateError}

type InputType = Seq[Int]

class Day01(rawInput: List[String]):
    def solvePart1(input: InputType): Int = input.sum
    def solvePart2(input: InputType): Int = input.scan(0)(_ + _).indexOf(-1)

    def solve(): Solution =
        val input = parseInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    private def parseInput: InputType =
        rawInput.head.map((step: Char) => if step == '(' then 1 else -1)
end Day01

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2015_01 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2015, 1) match
        case Some(raw_input) =>
            printSolution(Day01(raw_input).solve())
        case _ => impossibleStateError
end run_2015_01
// */
