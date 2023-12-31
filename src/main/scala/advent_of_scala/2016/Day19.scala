/** 2016/19: An Elephant Named Joseph
  *
  * Link: https://adventofcode.com/2016/day/19
  *
  * Difficulty: m
  *
  * Tags: bitwise linked-list mutation
  *
  * Answers: (1_842_613, 1_424_135)
  */
package advent_of_scala.year_2016

import advent_of_scala.base.Solution

class Day19(rawInput: List[String]):
    def solvePart1(input: Int) = ((input - Integer.highestOneBit(input)) << 1) + 1
    def solvePart2(input: Int) =
        case class Node(value: Int, var next: Node)

        var node = Node(1, null)
        node.next = node

        for n <- 2 to input do
            val next = Node(n, node.next)
            node.next = next
            node = next
        end for

        for i <- 1 to input / 2 do
            node = node.next

        for i <- input to 2 by -1 do
            node.next = node.next.next
            if i % 2 == 1 then node = node.next

        node.value
    end solvePart2

    def solve: Solution =
        val input = rawInput.head.toInt
        (solvePart1(input), solvePart2(input))
    end solve
end Day19

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2016_19 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    import advent_of_scala.base.impossibleStateError
    readLines(2016, 19) match
        case Some(raw_input) =>
            printSolution(Day19(raw_input).solve)
        case _ => impossibleStateError
end run_2016_19
// */
