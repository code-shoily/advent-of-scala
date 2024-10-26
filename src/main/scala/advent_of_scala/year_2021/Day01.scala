/** Day 1: Sonar Sweep
  *
  * Link: https://adventofcode.com/2021/day/1
  *
  * Difficulty: xs
  *
  * Tags: sequence
  *
  * Answers: (1139, 1103)
  */
package advent_of_scala.year_2021

import advent_of_scala.base.Solution

class Day01(rawInput: List[String]):
    def solve: Solution =
        val input = parsedInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    def solvePart1(input: Seq[Int]): Int = countIncrements(input, 1)

    private def countIncrements(sigs: Seq[Int], by: Int): Int =
        (0 until (sigs.length - by)).foldLeft(0)((incr, idx) =>
            incr + (if sigs(idx) < sigs(idx + by) then 1 else 0)
        )

    def solvePart2(input: Seq[Int]): Int = countIncrements(input, 3)

    def parsedInput: Seq[Int] = rawInput.map(_.toInt)
end Day01

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2021_01(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}

    readLines(2021, 1) match
        case Some(rawInput) =>
            val solver = Day01(rawInput)
            printSolution(solver.solve)
        case _ => impossibleStateError
    end match
end run_2021_01
// */
