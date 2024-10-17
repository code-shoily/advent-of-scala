/** 2021/13: Transparent Origami
  *
  * Link: https://adventofcode.com/2021/day/13
  *
  * Difficulty: l
  *
  * Tags: visual-result revisit
  *
  * Answers: (653, "LKREBPRK")
  */
package advent_of_scala.year_2021

import advent_of_scala.base.{Solution, impossibleStateError}
import Day13.*

class Day13(rawInput: List[String]):
    def solvePart1(dots: Set[Dot], folds: List[Fold]): Int =
        dots.map(folds.head.apply).size

    def solvePart2(dots: Set[Dot], folds: List[Fold]): String =
        val foldedDots = folds.foldLeft(dots)((dots, fold) => dots.map(fold.apply))
        val (width, height) = (foldedDots.map(_.x).max + 1, foldedDots.map(_.y).max + 1)
        val paper = Array.fill(height, width)(' ')

        for dot <- foldedDots do paper(dot.y)(dot.x) = '▉'

        paper map (_.mkString(" ")) mkString "\n"
    end solvePart2

    def solve: Solution =
        val (dots, folds) = parsedInput
        val part1 = solvePart1(dots, folds)
        val part2 = solvePart2(dots, folds)

        println(part2) // Read the input

        (part1, "LKREBPRK")
    end solve

    private def parsedInput =
        val sections = (rawInput mkString "\n").split("\n\n")
        val dots = sections(0).linesIterator.map(Dot.parse).toSet
        val folds = sections(1).linesIterator.map(Fold.parse).toList
        (dots, folds)
    end parsedInput
end Day13

object Day13:
    case class Dot(x: Int, y: Int)

    object Dot:
        def parse(line: String): Dot =
            line match
                case s"$x,$y" => Dot(x.toInt, y.toInt)
                case _        => impossibleStateError
    end Dot

    enum Fold:
        case Vertical(x: Int)
        case Horizontal(y: Int)

        def apply(dot: Dot): Dot =
            this match
                case Vertical(x: Int)   => Dot(fold(along = x)(dot.x), dot.y)
                case Horizontal(y: Int) => Dot(dot.x, fold(along = y)(dot.y))

        def fold(along: Int)(value: Int): Int =
            if value < along then value
            else along - (value - along)
    end Fold

    object Fold:
        def parse(line: String): Fold =
            line match
                case s"fold along x=$x" => Vertical(x.toInt)
                case s"fold along y=$y" => Horizontal(y.toInt)
                case _                  => impossibleStateError
    end Fold
end Day13

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2021_13 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    import advent_of_scala.base.impossibleStateError
    readLines(2021, 13) match
        case Some(raw_input) =>
            printSolution(Day13(raw_input).solve)
        case _ => impossibleStateError
end run_2021_13
// */
