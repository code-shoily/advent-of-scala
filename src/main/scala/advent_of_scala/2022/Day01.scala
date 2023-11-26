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

import scala.collection.mutable.PriorityQueue

import advent_of_scala.base.Solution

type InputType1 = PriorityQueue[Int]

class Day01(rawInput: List[String]):
    def solvePart1(input: InputType1): Int = input.head
    def solvePart2(input: InputType1): Int = input.take(3).sum

    def solve: Solution =
        val input = parsedInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    private def parsedInput: InputType1 =
        val pq = PriorityQueue[Int]()
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
@main def run_2022_01 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2022, 1) match
        case Some(raw_input) =>
            printSolution(Day01(raw_input).solve)
        case _ => sys.error("Could not read file")
end run_2022_01
// */
