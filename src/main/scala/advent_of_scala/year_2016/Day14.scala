/** 2016/14: One-time Pad
  *
  * Link: https://adventofcode.com/2016/day/14
  *
  * Difficulty: s
  *
  * Tags: md5 digest very-slow inline-input
  *
  * Answers: (15_168, 20_864)
  */
package advent_of_scala.year_2016

import advent_of_scala.base.Solution
import advent_of_scala.year_2016.Day14.*

import java.security.MessageDigest
import scala.util.matching.UnanchoredRegex

class Day14(rawInput: List[String]):
    def solve: Solution =
        val input = parsedInput
        (solvePart1(input), solvePart2(input))
    end solve

    def solvePart1(input: String): Int = generatePad(index => single(s"$input$index"))

    def solvePart2(input: String): Int = generatePad(index => stretched(s"$input$index"))

    private def parsedInput: String = rawInput.head.strip
end Day14

object Day14:
    val md5: MessageDigest = java.security.MessageDigest.getInstance("MD5")
    val three: UnanchoredRegex = "(.)\\1{2}".r.unanchored
    val five: UnanchoredRegex = "(.)\\1{4}".r.unanchored

    private def stretched(string: String): String =
        Iterator.iterate(string)(single).drop(2017).next()

    private def single(string: String): String =
        md5.digest(string.getBytes).map("%02x".format(_)).mkString

    private def generatePad(hash: Int => String): Int =
        def check(window: Seq[(String, Int)]): Boolean = window.head match
            case (three(t), _) => window.tail.exists {
                    case (five(f), _) if f == t => true
                    case _                      => false
                }
            case _ => false

        Iterator
            .from(0)
            .map(hash)
            .zipWithIndex
            .sliding(1001)
            .filter(check)
            .drop(63)
            .next()
            .head
            ._2
    end generatePad
end Day14

//*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2016_14(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2016, 14) match
        case Some(raw_input) =>
            printSolution(Day14(raw_input).solve)
        case _ => impossibleStateError
end run_2016_14
// */
