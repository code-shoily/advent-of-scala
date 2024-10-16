/** 2020/9: Encoding Error
  *
  * Link: https://adventofcode.com/2020/day/9
  *
  * Difficulty: s
  *
  * Tags: sliding-window two-pointer subarray-sum
  *
  * Answers: (15_353_384, 2_466_556)
  */
package advent_of_scala.year_2020

import scala.annotation.tailrec
import scala.util.chaining.*

import advent_of_scala.base.Solution
import Day09.*

class Day09(rawInput: List[String]):
    def solvePart1(input: InputType): Long =
        input.sliding(26).map(window =>
            if !isValid(
                  window.dropRight(1).sorted,
                  window.last
                )
            then window.last
            else 0
        ).find(_ > 0).get

    def solvePart2(input: InputType, target: Long): Long =
        subarraySum(input, target)
            .pipe({ case (from, to) => input.slice(from, to) })
            .pipe({ lst => lst.max + lst.min })

    def solve: Solution =
        val input = parsedInput
        val part1 = solvePart1(input)
        (part1, solvePart2(input, part1))
    end solve

    private def parsedInput: InputType = rawInput map { _.toLong }
end Day09

object Day09:
    type InputType = List[Long]

    def isValid(numList: List[Long], target: Long): Boolean =
        @tailrec
        def recur(from: Int, to: Int): Boolean =
            if from >= to then
                false
            else
                numList(from) + numList(to) match
                    case tooLarge if tooLarge > target => recur(from, to - 1)
                    case tooSmall if tooSmall < target => recur(from + 1, to)
                    case _                             => true

        recur(0, numList.length - 1)
    end isValid

    private def subarraySum(numList: List[Long], target: Long) =
        @tailrec
        def recur(from: Int, to: Int, total: Long): (Int, Int) =
            total match
                case tooLarge if tooLarge > target => recur(from + 1, to, total - numList(from))
                case tooSmall if tooSmall < target => recur(from, to + 1, total + numList(to))
                case _                             => (from, to)
        recur(0, 2, numList.head + numList(1))
    end subarraySum
end Day09

///*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2020_09(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2020, 9) match
        case Some(raw_input) =>
            printSolution(Day09(raw_input).solve)
        case _ => impossibleStateError
end run_2020_09
// */
