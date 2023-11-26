/** 2017/22: Sporifica Virus
  *
  * Link: https://adventofcode.com/2017/day/22
  *
  * Difficulty: s
  *
  * Tags: game-of-life slow grid-rotation
  *
  * Answers: (5447, 2_511_705)
  */
package advent_of_scala.year_2017

import advent_of_scala.base.{Solution, impossibleStateError}
import Day22.*
import Node.*

class Day22(rawInput: List[String]):
    def solvePart1(input: State): Int =
        def step(state: State): State = state.grid(state.location) match
            case Clean    => state.next(Infected, state.direction.ccw, 1)
            case Infected => state.next(Clean, state.direction.cw, 0)
            case _        => impossibleStateError

        Iterator.iterate(input)(step).drop(10_000).next().infected
    end solvePart1

    def solvePart2(input: State): Int =
        def step(state: State): State = state.grid(state.location) match
            case Clean    => state.next(Weakened, state.direction.ccw, 0)
            case Weakened => state.next(Infected, state.direction, 1)
            case Infected => state.next(Flagged, state.direction.cw, 0)
            case Flagged  => state.next(Clean, state.direction.cw.cw, 0)

        Iterator.iterate(input)(step).drop(10_000_000).next().infected
    end solvePart2

    def solve: Solution =
        val input = parsedInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    private def parsedInput: State =
        val points =
            for
                y <- rawInput.indices
                x <- rawInput.indices
                if rawInput(y)(x) == '#'
            yield Point(x, y)
        val start =
            points
                .map(_ -> Infected)
                .toMap
                .withDefaultValue(Clean)

        State(start, Point(rawInput.size / 2, rawInput.size / 2), Point(0, -1), 0)
    end parsedInput
end Day22

object Day22:
    case class Point(x: Int, y: Int):
        def +(other: Point): Point = Point(x + other.x, y + other.y)
        def cw: Point = Point(-y, x)
        def ccw: Point = Point(y, -x)
    end Point

    enum Node:
        case Clean, Weakened, Infected, Flagged

    case class State(grid: Map[Point, Node], location: Point, direction: Point, infected: Int):
        def next(node: Node, direction: Point, infect: Int): State =
            State(grid.updated(location, node), location + direction, direction, infected + infect)
end Day22

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2017_22 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    import advent_of_scala.base.impossibleStateError
    readLines(2017, 22) match
        case Some(raw_input) =>
            printSolution(Day22(raw_input).solve)
        case _ => impossibleStateError
end run_2017_22
// */
