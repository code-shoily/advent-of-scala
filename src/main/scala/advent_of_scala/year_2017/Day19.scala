/** 2017/19: A Series of Tubes
  *
  * Link: https://adventofcode.com/2017/day/19
  *
  * Difficulty: m
  *
  * Tags: graph graph-traversal assymmetric-result
  *
  * Answers: ("LIWQYKMRP", 16764)
  */
package advent_of_scala.year_2017

import advent_of_scala.base.Solution

import scala.annotation.tailrec

class Day19(rawInput: List[String]):
    def solve: Solution = Day19.traverse(rawInput)

object Day19:
    val (left, right, up, down) = (Point(-1, 0), Point(1, 0), Point(0, -1), Point(0, 1))
    val horizontal: Seq[Point] = Seq(left, right)
    private val vertical: Seq[Point] = Seq(up, down)

    private def traverse(input: Seq[String]): (String, Int) =
        @tailrec
        def doTraverse(
            location: Point,
            direction: Point,
            path: Seq[Char],
            steps: Int
        ): (String, Int) =
            def grid(point: Point): Char = input(point.y)(point.x)
            val nextLocation = location.delta(direction)
            grid(nextLocation) match
                case ' ' => (path.mkString, steps)
                case '+' =>
                    val Seq(first, second) =
                        if horizontal contains direction then vertical else horizontal
                    val nextDirection =
                        if grid(nextLocation.delta(first)).isSpaceChar then second else first
                    doTraverse(nextLocation, nextDirection, path, steps + 1)
                case c if c.isLetter =>
                    doTraverse(nextLocation, direction, path.appended(c), steps + 1)
                case _ => doTraverse(nextLocation, direction, path, steps + 1)
            end match
        end doTraverse

        doTraverse(Point(input.head.indexOf('|'), -1), down, Seq(), 0)
    end traverse

    case class Point(x: Int, y: Int):
        def delta(other: Point): Point = Point(x + other.x, y + other.y)
end Day19
/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2017_19(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2017, 19) match
        case Some(raw_input) =>
            printSolution(Day19(raw_input).solve)
        case _ => impossibleStateError
end run_2017_19
// */
