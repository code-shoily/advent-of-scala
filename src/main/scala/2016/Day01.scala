/** Day 1: No Time for a Taxicab
  *
  * Link: https://adventofcode.com/2016/day/1
  *
  * Difficulty: xs
  *
  * Tags: grid
  */
package advent_of_scala.year_2016

import advent_of_scala.base.Solution

type InputType = Seq[Instruction]

enum Direction:
    case North, South, East, West
enum Axis:
    case X, Y

case class State(
    twiceReached: Boolean,
    grid: Grid,
    history: Set[Point],
    result: Option[Point]
)
case class Instruction(direction: String, steps: Int)
case class Point(x: Int, y: Int):
    def distanceFromOrigin: Int = x.abs + y.abs

case class Grid(facing: Direction, currentLocation: Point)

def getNextGridParams(dir: Direction, lr: String) = dir match
    case Direction.North =>
        if lr == "R" then (Direction.East, 1, Axis.X)
        else (Direction.West, -1, Axis.X)
    case Direction.South =>
        if lr == "R" then (Direction.West, -1, Axis.X)
        else (Direction.East, 1, Axis.X)
    case Direction.East =>
        if lr == "R" then (Direction.South, -1, Axis.Y)
        else (Direction.North, 1, Axis.Y)
    case Direction.West =>
        if lr == "R" then (Direction.North, 1, Axis.Y)
        else (Direction.South, -1, Axis.Y)

def step(grid: Grid, instruction: Instruction): Grid =
    val Point(x, y) = grid.currentLocation
    val steps = instruction.steps

    val (direction, multiplier, axis) =
        getNextGridParams(grid.facing, instruction.direction)
    if axis == Axis.X then Grid(direction, Point(x + multiplier * steps, y))
    else Grid(direction, Point(x, y + multiplier * steps))
end step

class Day01(rawInput: List[String]):
    def solvePart1(input: InputType): Int =
        input
            .foldLeft(Grid(Direction.North, Point(0, 0)))(step)
            .currentLocation
            .distanceFromOrigin

    def solvePart2(input: InputType): Int =
        input
            .scanLeft(
              State(false, Grid(Direction.North, Point(0, 0)), Set[Point](), None)
            )(revisitReducer)
            .find(_.twiceReached == true)
            .flatMap(_.result.map(_.distanceFromOrigin))
            .get

    private def revisitReducer(state: State, instruction: Instruction): State =
        val previousGrid = state.grid
        val nextGrid = step(previousGrid, instruction)

        exploreVisits(state.history, previousGrid, nextGrid) match
            case Left(point) =>
                State(true, nextGrid, state.history, Some(point))
            case Right(updatedHistory) =>
                State(false, nextGrid, updatedHistory, None)
        end match
    end revisitReducer

    private def exploreVisits(
        history: Set[Point],
        previous: Grid,
        next: Grid
    ): Either[Point, Set[Point]] =
        val points = (previous.currentLocation, next.currentLocation) match
            case (Point(x0, y0), Point(x1, y1)) if x0 == x1 =>
                (if y0 >= y1 then (y1 to (y0 - 1)) else ((y0 + 1) to y1))
                    .map(Point(x0, _))
            case (Point(x0, y0), Point(x1, y1)) if y0 == y1 =>
                (if x0 >= x1 then (x1 to (x0 - 1)) else ((x0 + 1) to x1))
                    .map(Point(_, y0))
            case _ =>
                sys.error("Unreachable State")

        points.find(history.contains(_)) match
            case Some(point) => Left(point)
            case _           => Right(history ++ points)
    end exploreVisits

    def solve(): Solution =
        val input = parseInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    private def parseInput: InputType =
        rawInput.head
            .split(",\\s?")
            .map(instruction =>
                Instruction(instruction.slice(0, 1), instruction.substring(1).toInt)
            )
end Day01

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2016_01 =
  import utils.io.{readLines, printSolution}
  readLines(2016, 1) match
    case Some(raw_input) =>
      printSolution(Day01(raw_input).solve())
    case _ => sys.error("Could not read file")
// */
