/** 2020/3: Perfectly Spherical Houses in a Vacuu
  *
  * Link: https://adventofcode.com/2020/day/3
  *
  * Difficulty: xs
  *
  * Tags: set 2D
  *
  * Answers: (2081, 2341)
  */
package advent_of_scala.year_2020

import advent_of_scala.base.Solution
import Day03.*
import advent_of_scala.year_2020
import advent_of_scala.year_2020.Direction.*

import scala.annotation.targetName
import scala.collection.immutable.HashSet

class Day03(rawInput: List[String]):
    def solvePart1(input: InputType): Int = visits(input).housesVisited
    def solvePart2(input: InputType): Int = splitBetweenSantas(input) match
        case (santa, robot) => (visits(santa) & visits(robot)).size

    def solve: Solution =
        val input = parsedInput
        (solvePart1(input), solvePart2(input))
    end solve

    private def parsedInput: InputType = rawInput.head.toList.map(Direction.apply)
end Day03

object Day03:
    type InputType = List[Direction]

    def visits(input: InputType): Visit = (input foldLeft Visit()) { _ ~>> _ }

    private def splitBetweenSantas(input: InputType): (InputType, InputType) =
        input.sliding(2, 2).toList.collect { case a :: b :: _ => (a, b) }.unzip
end Day03

enum Direction:
    case Up, Down, Left, Right

object Direction:
    def apply(ch: Char): Direction =
        ch match
            case '^' => Up
            case 'v' => Down
            case '>' => Left
            case '<' => Right
end Direction

case class Point(x: Int, y: Int):
    @targetName("moveTo")
    def ~>>(to: Direction): Point =
        to match
            case Up    => Point(x, y - 1)
            case Down  => Point(x, y + 1)
            case Left  => Point(x - 1, y)
            case Right => Point(x + 1, y)
end Point

case class Visit(houses: HashSet[Point] = HashSet(), currentHouse: Point = Point(0, 0)):
    def housesVisited: Int = houses.size

    @targetName("moveTo")
    def ~>>(direction: Direction): Visit = Visit(houses + currentHouse, currentHouse ~>> direction)

    @targetName("housesVisited")
    def &(visit: Visit): HashSet[Point] = houses union visit.houses
end Visit

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2020_03(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}

    readLines(2020, 3) match
        case Some(raw_input) =>
            printSolution(Day03(raw_input).solve)
        case _ => impossibleStateError
end run_2020_03
// */
