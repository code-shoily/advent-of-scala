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

import advent_of_scala.base.Solution
import advent_of_scala.year_2023.Day01.*

import scala.util.chaining.*
import scala.util.matching.Regex

class Day01(rawInput: List[String]):
    def solve: Solution =
        val input = parsedInput

        def solver(finder: String => Int)(input: InputType) = input map finder reduce { _ + _ }
        (
          solver(numericDigits)(input),
          solver(numericOrWordedDigits)(input)
        )
    end solve

    private def parsedInput: InputType = rawInput
end Day01

object Day01:
    type InputType = List[String]

    private val nums = List("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    private val regex: Regex = ("\\d" :: nums).mkString("(", "|", ")").r
    private val regexRev = ("\\d" :: (nums map { _.reverse })).mkString("(", "|", ")").r

    private def numericDigits(line: String) = "\\d".r.findAllIn(line).toArray pipe { a =>
        tr(a.head) * 10 + tr(a.last)
    }

    private def tr(value: String, invert: Boolean = false): Int =
        (if invert then value.reverse else value) match
            case "one" | "1"   => 1
            case "two" | "2"   => 2
            case "three" | "3" => 3
            case "four" | "4"  => 4
            case "five" | "5"  => 5
            case "six" | "6"   => 6
            case "seven" | "7" => 7
            case "eight" | "8" => 8
            case "nine" | "9"  => 9

    private def numericOrWordedDigits(line: String) =
        tr(regex.findFirstIn(line).get) * 10 + tr(
          regexRev.findFirstIn(line.reverse).get,
          invert = true
        )
end Day01

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2023_01(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2023, 1) match
        case Some(raw_input) =>
            printSolution(Day01(raw_input).solve)
        case _ => impossibleStateError
end run_2023_01
// */
