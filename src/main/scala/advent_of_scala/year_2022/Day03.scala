/** 2022/3: Rucksack Reorganization
  *
  * Link: https://adventofcode.com/2022/day/3
  *
  * Difficulty: xs
  *
  * Tags: group
  *
  * Answers: (8233, 2821)
  */
package advent_of_scala.year_2022

import advent_of_scala.base.Solution
import advent_of_scala.year_2022.Day03.*

class Day03(rawInput: List[String]):
    def solve: Solution =
        val input = parsedInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    def solvePart1(input: InputType): Int = solve(input.map(byHalf))

    def solvePart2(input: InputType): Int = solve(input.grouped(3).toList)

    private def solve(input: List[List[Array[Char]]]): Int =
        input.map(getCommonItem(_*)).map(getPriority).sum

    private def parsedInput: InputType = rawInput.map(_.toCharArray())
end Day03

object Day03:
    type InputType = List[Array[Char]]

    private def byHalf(items: Array[Char]) = items.splitAt(items.length / 2).toList
    private def getCommonItem(items: Array[Char]*) = items.reduce(_ intersect _).head
    private def getPriority(itemType: Char) = itemType match
        case lowerCase if ('a' to 'z').contains(lowerCase) => (itemType.toInt % 'a'.toInt) + 1
        case upperCase if ('A' to 'Z').contains(upperCase) => (itemType.toInt % 'A'.toInt) + 27
end Day03

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2022_03(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2022, 3) match
        case Some(raw_input) =>
            printSolution(Day03(raw_input).solve)
        case _ => impossibleStateError
end run_2022_03
// */
