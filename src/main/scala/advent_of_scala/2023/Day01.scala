/** 2023/1: Trebuchet?!
  *
  * Link: https://adventofcode.com/2023/day/1
  *
  * Difficulty: s
  *
  * Tags: regex
  *
  * Answers: (53_194, 54_249)
  */
package advent_of_scala.year_2023

import scala.util.chaining.*
import scala.util.matching.Regex.*

import advent_of_scala.base.{Solution, impossibleStateError}
import Day01.*

class Day01(rawInput: List[String]):
    def solver(finder: String => Int)(input: InputType) = input map finder reduce { _ + _ }
    def solvePart1 = solver(numericDigits)
    def solvePart2 = solver(numericOrWordedDigits)

    def solve: Solution =
        val input = parsedInput
        (solvePart1(input), solvePart2(input))
    end solve

    private def parsedInput: InputType = rawInput
end Day01

object Day01:
    type InputType = List[String]
    private final val numbers =
        List("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    def numericDigits(line: String) = "\\d".r.findAllMatchIn(line).toArray pipe { a =>
        tr(a.head) * 10 + tr(a.last)
    }

    private val regex = ("\\d" :: numbers).mkString("(", "|", ")").r
    private val regexRev = ("\\d" :: (numbers map { _.reverse })).mkString("(", "|", ")").r

    def numericOrWordedDigits(line: String) =
        (regex findFirstMatchIn (line) match
            case Some(data) => tr(data) * 10
            case _          => impossibleStateError
        ) + (regexRev findFirstMatchIn (line.reverse) match
            case Some(data) => tr(data, true)
            case _          => impossibleStateError
        )

    private def tr(value: Match, invert: Boolean = false) =
        (if invert then value.toString().reverse else value.toString()) match
            case "one" | "1"   => 1
            case "two" | "2"   => 2
            case "three" | "3" => 3
            case "four" | "4"  => 4
            case "five" | "5"  => 5
            case "six" | "6"   => 6
            case "seven" | "7" => 7
            case "eight" | "8" => 8
            case "nine" | "9"  => 9
end Day01

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2023_01 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    import advent_of_scala.base.impossibleStateError
    readLines(2023, 1) match
        case Some(raw_input) =>
            printSolution(Day01(raw_input).solve)
        case _ => impossibleStateError
end run_2023_01
// */
