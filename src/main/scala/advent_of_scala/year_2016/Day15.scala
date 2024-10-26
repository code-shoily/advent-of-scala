/** 2016/15: Timing is Everything
  *
  * Link: https://adventofcode.com/2016/day/15
  *
  * Difficulty: s
  *
  * Tags: sequence
  *
  * Answers: (317_371, 2_080_951)
  */
package advent_of_scala.year_2016

import advent_of_scala.base.Solution
import advent_of_scala.year_2016.Day15.*

import scala.util.chaining.*

class Day15(rawInput: List[String]):
    def solve: Solution =
        val input = parsedInput
        (solvePart1(input), solvePart2(input))
    end solve

    def solvePart1(input: InputType): Int = input pipe firstTime

    def solvePart2(input: InputType): Int =
        input.appended(Disc(input.size + 1, 11, 0)) pipe firstTime

    private def parsedInput: InputType = rawInput.map { line =>
        line.split("\\D+").tail map { _.toInt } pipe { tokens =>
            Disc(tokens(0), tokens(1), tokens(3))
        }
    }
end Day15

object Day15:
    type InputType = Seq[Disc]

    private def firstTime(discs: Seq[Disc]) =
        Iterator
            .from(0)
            .filter(time => discs.forall(_.pass(time)))
            .next()

    case class Disc(index: Int, size: Int, offset: Int):
        def pass(time: Int): Boolean = (time + index + offset) % size == 0
end Day15

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2016_15(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2016, 15) match
        case Some(raw_input) =>
            printSolution(Day15(raw_input).solve)
        case _ => impossibleStateError
end run_2016_15
// */
