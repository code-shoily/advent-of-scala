/** 2017/2: Corruption Checksum
  *
  * Link: https://adventofcode.com/2017/day/2
  *
  * Difficulty: xs
  *
  * Tags: arithmetic checksum
  *
  * Answers: (32020, 236)
  */
package advent_of_scala.year_2017

import advent_of_scala.base.{Solution, impossibleStateError}
import advent_of_scala.year_2017.Day02.*

class Day02(rawInput: List[String]):
    def solve: Solution =
        val input = parsedInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    def solvePart1(input: InputType): Int = input map { row => row.last - row.head } reduce (_ + _)

    def solvePart2(input: InputType): Int = input map dividingPair reduce (_ + _)

    private def parsedInput: InputType = rawInput map {
        _.split("\n") flatMap { _.split("\t") map (_.toInt) } sortBy identity
    }
end Day02

object Day02:
    type InputType = List[Array[Int]]

    private def dividingPair(row: Array[Int]) =
        (for
            i <- row.indices
            j <- row.indices
            if j > i
            if row(j) % row(i) == 0
        yield (row(i), row(j))) match
            case value if value.length == 1 => value.head._2 / value.head._1
            case _                          => impossibleStateError
end Day02

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2017_02(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2017, 2) match
        case Some(raw_input) =>
            printSolution(Day02(raw_input).solve)
        case _ => impossibleStateError
end run_2017_02
// */
