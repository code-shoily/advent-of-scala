/** 2020/2: Password Philosophy
  *
  * Link: https://adventofcode.com/2020/day/2
  *
  * Difficulty: xs
  *
  * Tags: predicates
  *
  * Answers: (607, 321)
  */
package advent_of_scala.year_2020

import advent_of_scala.base.{Solution, impossibleStateError}
import advent_of_scala.year_2020.Day02.*

class Day02(rawInput: List[String]):
    def solve: Solution =
        val input = parsedInput
        (
          input count { _.isPasswordValidV1 },
          input count { _.isPasswordValidV2 }
        )
    end solve

    private def parsedInput: InputType = rawInput map Policy.fromLine
end Day02

object Day02:
    type InputType = List[Policy]

    case class Policy(from: Int, to: Int, char: Char, password: String):
        def isPasswordValidV1: Boolean =
            val charCount = password.count(_ == char)
            from <= charCount && to >= charCount

        def isPasswordValidV2: Boolean =
            val a = password.charAt(from - 1)
            val b = password.charAt(to - 1)
            (a == char && b != a) || (b == char && a != b)
        end isPasswordValidV2
    end Policy

    private object Policy:
        def fromLine(line: String): Policy =
            line.split(" ").toList match
                case range :: char :: password :: Nil =>
                    val (from, to) = getRange(range)
                    Policy(from, to, char.stripSuffix(":").head, password)
                case _ => impossibleStateError
            end match
        end fromLine

        private def getRange(range: String) =
            val parsed = range.split("-") map (_.toInt)
            (parsed.head, parsed.last)
    end Policy
end Day02

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2020_02(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2020, 2) match
        case Some(raw_input) =>
            printSolution(Day02(raw_input).solve)
        case _ => impossibleStateError
end run_2020_02
// */
