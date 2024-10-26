/** Day 1: Calorie Counting
  *
  * Link: https://adventofcode.com/2022/day/1
  *
  * Difficulty: xs
  *
  * Tags: priority-queue
  *
  * Answers: (70_720, 207_148)
  */
package advent_of_scala.year_2022

import advent_of_scala.base.Solution

import scala.collection.mutable

class Day01(rawInput: List[String]):
    def solve: Solution =
        val input = parsedInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    def solvePart1(input: mutable.PriorityQueue[Int]): Int = input.head

    def solvePart2(input: mutable.PriorityQueue[Int]): Int = input.take(3).sum

    private def parsedInput: mutable.PriorityQueue[Int] =
        val pq = mutable.PriorityQueue[Int]()
        val calories =
            rawInput.foldLeft(List[Int](0))((acc, x) =>
                if x == "" then 0 :: acc
                else (x.toInt + acc.head) :: acc.tail
            )

        pq.enqueue(calories*)
        pq
    end parsedInput
end Day01

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2022_01(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2022, 1) match
        case Some(raw_input) =>
            printSolution(Day01(raw_input).solve)
        case _ => impossibleStateError
end run_2022_01
// */
