/** 2016/2: Bathroom Security
  *
  * Link: https://adventofcode.com/2016/day/2
  *
  * Difficulty: xs
  *
  * Tags: walk string-result
  *
  * Answers: ("76792", "A7AC3")
  */
package advent_of_scala.year_2016

import advent_of_scala.base.Solution
import advent_of_scala.year_2016.Day02.*

class Day02(rawInput: List[String]):
    private val numPad1 = List(
      List(" ", " ", " ", " ", " "),
      List(" ", "1", "2", "3", " "),
      List(" ", "4", "5", "6", " "),
      List(" ", "7", "8", "9", " "),
      List(" ", " ", " ", " ", " ")
    )

    private val numPad2 = List(
      List(" ", " ", " ", " ", " ", " ", " "),
      List(" ", " ", " ", "1", " ", " ", " "),
      List(" ", " ", "2", "3", "4", " ", " "),
      List(" ", "5", "6", "7", "8", "9", " "),
      List(" ", " ", "A", "B", "C", " ", " "),
      List(" ", " ", " ", "D", " ", " ", " "),
      List(" ", " ", " ", " ", " ", " ", " ")
    )
    def solve: Solution =
        val input = parsedInput
        val solver = crackCode(input)
        (solver(numPad1, (2, 2)), solver(numPad2, (1, 3)))
    end solve

    private def crackCode(input: InputType)(pad: List[List[String]], startAt: (Int, Int)) =
        input.foldLeft((Vector[String](), startAt)) { case ((code, pos), directions) =>
            val (x, y) =
                directions.foldLeft(pos) { case ((x, y), direction) =>
                    direction match
                        case "U" if !pad(x - 1)(y).isBlank => (x - 1, y)
                        case "D" if !pad(x + 1)(y).isBlank => (x + 1, y)
                        case "L" if !pad(x)(y - 1).isBlank => (x, y - 1)
                        case "R" if !pad(x)(y + 1).isBlank => (x, y + 1)
                        case _                             => (x, y)
                    end match
                }

            (code :+ pad(x)(y), (x, y))
        }._1 mkString ""

    private def parsedInput: InputType = rawInput map { _.split("").toList }
end Day02

object Day02:
    type InputType = List[List[String]]

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2016_02(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2016, 2) match
        case Some(raw_input) =>
            printSolution(Day02(raw_input).solve)
        case _ => impossibleStateError
end run_2016_02
// */
