/** 2022/4: ???
  *
  * Link: https://adventofcode.com/2022/day/4
  *
  * Difficulty: xs
  *
  * Tags: range
  *
  * Answers: (518, 909)
  */
package advent_of_scala.year_2022

import advent_of_scala.base.Solution
import advent_of_scala.year_2022.Day04.*

class Day04(rawInput: List[String]):
    def solve: Solution =
        val input = parsedInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    def solvePart1(input: InputType): Int = input.count(fullyContains)

    def solvePart2(input: InputType): Int = input.count(overlaps)

    private def parsedInput: InputType =
        def toRange(rr: String): (Int, Int) =
            val Array(a, b) = rr.split("-") map (_.toInt)
            (a, b)
        rawInput map (_.split(",").toSeq) map { case Seq(a, b) =>
            (toRange(a), toRange(b))
        }
    end parsedInput
end Day04

object Day04:
    type InputType = List[((Int, Int), (Int, Int))]

    private def fullyContains(a: (Int, Int), b: (Int, Int)): Boolean = (a, b) match
        case ((a1, b1), (a2, b2)) if a1 <= a2 && b1 >= b2 => true
        case ((a1, b1), (a2, b2)) if a2 <= a1 && b2 >= b1 => true
        case _                                            => false

    private def overlaps(a: (Int, Int), b: (Int, Int)): Boolean = (a, b) match
        case ((a1, b1), (a2, b2)) if b1 >= a2 && b1 <= b2 => true
        case ((a1, b1), (a2, b2)) if b2 >= a1 && b2 <= b1 => true
        case _                                            => false
end Day04

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2022_04(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2022, 4) match
        case Some(raw_input) =>
            printSolution(Day04(raw_input).solve)
        case _ => impossibleStateError
end run_2022_04
// */
