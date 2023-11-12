/** 2015/2: I Was Told There Would Be No Math
  *
  * Link: https://adventofcode.com/2015/day/2
  *
  * Difficulty:
  *
  * Tags:
  *
  * Answers: (1_606_483, 3_842_356)
  */
package advent_of_scala.year_2015

import advent_of_scala.base.{Solution, impossibleStateError}

type InputType = List[Dimension]

case class Dimension(width: Int, length: Int, height: Int):
    def wrappingPaperNeeded = smallestArea + surfaceArea
    def ribbonNeeded = smallestPerimeter + volume
    private def smallestArea = List(width * length, length * height, height * width).min
    private def surfaceArea = 2 * (width * length + length * height + height * width)
    private def volume = width * length * height
    private def smallestPerimeter = List(width, length, height).sorted.take(2).map(_ * 2).sum
end Dimension

class Day02(rawInput: List[String]):
    def solvePart1(input: InputType): Int = input.map(_.wrappingPaperNeeded).sum
    def solvePart2(input: InputType): Int = input.map(_.ribbonNeeded).sum

    def solve: Solution =
        val input = parseInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    private def parseInput: InputType =
        rawInput.map {
            line =>
                line.split("x").toList match
                    case w :: l :: h :: Nil => Dimension(w.toInt, l.toInt, h.toInt)
                    case _                  => sys.error("Can't have this")
        }
end Day02

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2015_02 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2015, 2) match
        case Some(raw_input) =>
            printSolution(Day02(raw_input).solve)
        case _ => impossibleStateError
end run_2015_02
// */
