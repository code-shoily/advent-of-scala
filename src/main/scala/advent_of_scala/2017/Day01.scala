/** Day 1: Inverse Capta
  *
  * Link: https://adventofcode.com/2017/day/1
  *
  * Difficulty: xs
  *
  * Tags: linked-list
  *
  * Answers: (1089, 1156)
  */
package advent_of_scala.year_2017

import scala.annotation.tailrec

import advent_of_scala.base.Solution

class Day01(rawInput: List[String]):
    def solvePart1(input: Seq[Int]): Int =
        val head = input.head

        @tailrec
        def recurse(lst: Seq[Int], total: Int = 0): Int =
            lst match
                case x :: (y :: rest) if x == y =>
                    recurse(y :: rest, total + x)
                case x :: Nil if x == head => total + x
                case x :: rest             => recurse(rest, total)

        recurse(input)
    end solvePart1

    def solvePart2(input: Seq[Int]): Int =
        @tailrec
        def recurse(list1: Seq[Int], list2: Seq[Int], total: Int = 0): Int =
            (list1, list2) match
                case (x :: rest1, y :: rest2) if x == y =>
                    recurse(rest1, rest2, total + x + y)
                case (x :: rest1, y :: rest2) => recurse(rest1, rest2, total)
                case (Nil, Nil)               => total

        val (firstHalf, secondHalf) = input.splitAt(input.length / 2)

        recurse(firstHalf, secondHalf)
    end solvePart2

    def solve: Solution =
        val input = parsedInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    def parsedInput: Seq[Int] =
        rawInput.head.toVector.map(_.toInt - '0'.toInt).toList
end Day01

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2017_01 =
    import advent_of_scala.utils.IO.{readLines, printSolution}

    readLines(2017, 1) match
        case Some(rawInput) =>
            val solver = Day01(rawInput)
            printSolution(solver.solve)
        case _ => sys.error("Could not read file")
    end match
end run_2017_01
// */
