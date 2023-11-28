/** 2016/15: Timing is Everything
  *
  * Link: https://adventofcode.com/2016/day/15
  *
  * Difficulty: s
  *
  * Tags: sequence-query
  *
  * Answers: (317_371, 2_080_951)
  */
package advent_of_scala.year_2016

import scala.util.chaining.*

import advent_of_scala.base.Solution
import Day15.*

class Day15(rawInput: List[String]):
    def solvePart1(input: InputType) = input pipe firstTime
    def solvePart2(input: InputType) = input.appended(Disc(input.size + 1, 11, 0)) pipe firstTime

    def solve: Solution =
        val input = parsedInput
        (solvePart1(input), solvePart2(input))
    end solve

    private def parsedInput: InputType = rawInput.map { line =>
        line.split("\\D+").tail map { _.toInt } pipe { tokens =>
            Disc(tokens(0), tokens(1), tokens(3))
        }
    }
end Day15

object Day15:
    type InputType = Seq[Disc]

    case class Disc(index: Int, size: Int, offset: Int):
        def pass(time: Int) = (time + index + offset) % size == 0

    def firstTime(discs: Seq[Disc]) =
        Iterator
            .from(0)
            .filter(time => discs.forall(_ pass (time)))
            .next()
end Day15

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2016_15 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    import advent_of_scala.base.impossibleStateError
    readLines(2016, 15) match
        case Some(raw_input) =>
            printSolution(Day15(raw_input).solve)
        case _ => impossibleStateError
end run_2016_15
// */
