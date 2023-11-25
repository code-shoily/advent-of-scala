/** 2020/2: Password Philosophy
  *
  * Link: https://adventofcode.com/2020/day/2
  *
  * Difficulty: xs
  *
  * Tags: validation
  *
  * Answers: (607, 321)
  */
package advent_of_scala.year_2020

import advent_of_scala.base.{Solution, impossibleStateError}

type InputType2 = List[Policy]

class Day02(rawInput: List[String]):
    def solve: Solution =
        val input = parsedInput
        (
          input count { _.isPasswordValidV1 },
          input count { _.isPasswordValidV2 }
        )
    end solve

    private def parsedInput: InputType2 = rawInput map (Policy.fromLine(_))
end Day02

case class Policy(from: Int, to: Int, char: Char, password: String):
    def isPasswordValidV1 =
        val charCount = password.filter(_ == char).length()
        from <= charCount && to >= charCount

    def isPasswordValidV2 =
        val a = password.charAt(from - 1)
        val b = password.charAt(to - 1)
        (a == char && b != a) || (b == char && a != b)
    end isPasswordValidV2
end Policy

object Policy:
    def fromLine(line: String) =
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

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2020_02 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    import advent_of_scala.base.impossibleStateError
    readLines(2020, 2) match
        case Some(raw_input) =>
            printSolution(Day02(raw_input).solve)
        case _ => impossibleStateError
end run_2020_02
// */
