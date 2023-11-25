/** 2021/5: Hydrothermal Venture
  *
  * Link: https://adventofcode.com/2021/day/5
  *
  * Difficulty: xs
  *
  * Tags: geometry set
  *
  * Answers: (4655, 20_500)
  */
package advent_of_scala.year_2021

import advent_of_scala.base.{Solution, impossibleStateError}

type InputType5 = List[Line]

class Day05(rawInput: List[String]):
    def solveForLines(input: InputType5, f: (Line => Boolean)) =
        input filter (f) flatMap (_.points) groupBy (identity) count (_._2.length > 1)
    def solve: Solution =
        val input = parsedInput
        val part1 = solveForLines(input, (line: Line) => line.isOrthogonal)
        val part2 = solveForLines(input, (_: Line) => true)
        (part1, part2)
    end solve

    private def parsedInput: InputType5 = rawInput.map(Line.fromString(_)).toSeq
end Day05

def toPoint(data: String): Point = data.split(",").toList match
    case x :: y :: _ => Point(x.toInt, y.toInt)
    case _           => impossibleStateError

case class Point(x: Int, y: Int)
case class Line(from: Point, to: Point):
    def isOrthogonal = from.x == to.x || from.y == to.y

    def points =
        val (Point(x1, y1), Point(x2, y2)) = (from, to)
        val xs = if x1 < x2 then (x1 to x2) else (x2 to x1).reverse
        val ys = if y1 < y2 then (y1 to y2) else (y2 to y1).reverse

        if isOrthogonal then
            if x1 == x2 then ys map { (x1, _) }
            else xs map { (_, y1) }
        else
            xs zip ys
        end if
    end points
end Line

object Line:
    def fromString(lineString: String) = lineString.split(" -> ").map(toPoint).toList match
        case x :: y :: _ => Line(x, y)
        case _           => impossibleStateError
end Line

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2021_05 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2021, 5) match
        case Some(raw_input) =>
            printSolution(Day05(raw_input).solve)
        case _ => impossibleStateError
end run_2021_05
// */
