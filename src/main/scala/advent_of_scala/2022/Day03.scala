/** 2022/3: Rucksack Reorganization
  *
  * Link: https://adventofcode.com/2022/day/3
  *
  * Difficulty: xs
  *
  * Tags: chunk char
  *
  * Answers: (8233, 2821)
  */
package advent_of_scala.year_2022

import advent_of_scala.base.{Solution, impossibleStateError}

type InputType = List[Array[Char]]

class Day03(rawInput: List[String]):
    def solvePart1(input: InputType): Int = solver(input.map(byHalf))
    def solvePart2(input: InputType): Int = solver(input.grouped(3).toList)
    def solver(input: List[List[Array[Char]]]) = input.map(getCommonItem(_*)).map(getPriority).sum

    def solve: Solution =
        val input = parseInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    private def parseInput: InputType = rawInput.map(_.toCharArray())
end Day03

def byHalf(items: Array[Char]) = items.splitAt(items.length / 2).toList
def getCommonItem(items: Array[Char]*) = items.reduce(_ intersect _).head
def getPriority(itemType: Char) = itemType match
    case lowerCase if ('a' to 'z').contains(lowerCase) => (itemType.toInt % 'a'.toInt) + 1
    case upperCase if ('A' to 'Z').contains(upperCase) => (itemType.toInt % 'A'.toInt) + 27

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2022_03 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2022, 3) match
        case Some(raw_input) =>
            printSolution(Day03(raw_input).solve)
        case _ => impossibleStateError
end run_2022_03
// */
