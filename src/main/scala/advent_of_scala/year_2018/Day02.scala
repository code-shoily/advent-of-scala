/** 2018/2: Inventory Management System
  *
  * Link: https://adventofcode.com/2018/day/2
  *
  * Difficulty: xs
  *
  * Tags: string set mixed-result
  *
  * Answers: (7221, "mkcdflathzwsvjxrevymbdpoq")
  */
package advent_of_scala.year_2018

import advent_of_scala.base.Solution
import Day02.*

class Day02(rawInput: List[String]):
    def solvePart1(input: InputType): Int =
        input.map(interestingFrequencies).foldLeft((0, 0)) { case ((twos, threes), set) =>
            (incrIfContains(set, twos, 2), incrIfContains(set, threes, 3))
        } match
            case (a, b) => a * b

    def solvePart2(input: InputType): String =
        input.head.indices.map(idx =>
            val withoutCharAtIdx = input map { StringBuilder(_).deleteCharAt(idx) }
            withoutCharAtIdx.groupBy(identity).collect {
                case (id, vals) if vals.length == 2 => id.toString
            }.flatten.mkString
        ).filterNot(_.isEmpty).head

    def solve: Solution =
        val input = parsedInput
        (solvePart1(input), solvePart2(input))
    end solve

    private def parsedInput: InputType = rawInput
end Day02

object Day02:
    type InputType = List[String]

    private def incrIfContains(set: Set[Int], result: Int, target: Int): Int =
        if set.contains(target) then result + 1 else result

    private def interestingFrequencies(line: String): Set[Int] =
        (line groupBy identity map (_._2.length) filter (List(2, 3) contains _)).toSet
end Day02

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2018_02(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}

    readLines(2018, 2) match
        case Some(raw_input) =>
            printSolution(Day02(raw_input).solve)
        case _ => impossibleStateError
    end match
end run_2018_02
// */
