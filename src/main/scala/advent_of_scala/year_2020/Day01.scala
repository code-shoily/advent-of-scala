/** Day 1: Report Repair
  *
  * Link: https://adventofcode.com/2020/day/1
  *
  * Difficulty: xs
  *
  * Tags: n-sum two-pointer
  *
  * Answers: (1_014_624, 80_072_256)
  */
package advent_of_scala.year_2020

import advent_of_scala.base.{Solution, impossibleStateError}

class Day01(rawInput: List[String]):
    def solve: Solution =
        val input = parsedInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    def solvePart1(input: Seq[Int]): Int = twoSumMultiplied(input, 2020) match
        case Some(value) => value
        case None        => impossibleStateError

    def solvePart2(input: Seq[Int]): Int =
        input.zipWithIndex
            .flatMap(entry =>
                twoSumMultiplied(input.drop(entry(1) + 1), 2020 - entry(0)) match
                    case None       => None
                    case Some(mult) => Some(entry(0) * mult)
            )
            .head

    private def twoSumMultiplied(input: Seq[Int], target: Int): Option[Int] =
        var result = 0
        var left = 0
        var right = input.length - 1
        while left < right do
            val currentSum = input(left) + input(right)
            if currentSum < target then left += 1
            else if currentSum > target then right -= 1
            else return Some(input(left) * input(right))
        end while

        None
    end twoSumMultiplied

    private def parsedInput: Seq[Int] = rawInput.map(_.toInt).sorted
end Day01

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2020_01(): Unit =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2020, 1) match
        case Some(raw_input) =>
            printSolution(Day01(raw_input).solve)
        case _ => sys.error("Could not read file")
end run_2020_01
// */
