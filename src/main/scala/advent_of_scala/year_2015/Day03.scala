/** 2015/3: Perfectly Spherical Houses in a Vacuum
  *
  * Link: https://adventofcode.com/2015/day/3
  *
  * Difficulty: xs
  *
  * Tags: grid2d walk set
  *
  * Answers: (2081, 2341)
  */
package advent_of_scala.year_2015

import scala.annotation.tailrec

import advent_of_scala.base.Solution
import Day03.*

class Day03(rawInput: List[String]):
    def solvePart1(input: InputType): Int =
        val delivery = Delivery()
        delivery.followDirections(input)
        delivery.visitedHouses.size

    def solvePart2(input: InputType): Int =
        val santaVisits = Delivery()
        val roboVisits = Delivery()
        val (santaDirections, roboDirections): (List[Direction], List[Direction]) =
            divideInstructions(input, List[Direction](), List[Direction]())

        santaVisits.followDirections(santaDirections)
        roboVisits.followDirections(roboDirections)

        (santaVisits.visitedHouses | roboVisits.visitedHouses).size
    end solvePart2

    def solve: Solution =
        val input = parsedInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    private def parsedInput: InputType = rawInput.head.map(asDirection).toList
end Day03

object Day03:
    type InputType = List[Direction]

    enum Direction:
        case Up, Down, Left, Right

    case class House(x: Int, y: Int):
        def getNeighbour(xDiff: Int, yDiff: Int): House = House(x + xDiff, y + yDiff)

    private class Delivery:
        private var house = House(0, 0)
        var visitedHouses: Set[House] = Set[House]() + house

        def followDirections(directions: InputType): Unit = directions.foreach(nextHouse)

        private def nextHouse(direction: Direction): Unit =
            val House(x, y) = house
            val newHouse =
                direction match
                    case Direction.Up    => house.getNeighbour(0, 1)
                    case Direction.Down  => house.getNeighbour(0, -1)
                    case Direction.Left  => house.getNeighbour(-1, 0)
                    case Direction.Right => house.getNeighbour(1, 0)

            house = newHouse
            visitedHouses += house
        end nextHouse
    end Delivery

    private def asDirection(d: Char): Direction = d match
        case '^' => Direction.Up
        case 'v' => Direction.Down
        case '<' => Direction.Left
        case '>' => Direction.Right

    @tailrec
    final private def divideInstructions(
        instructions: InputType,
        part1: InputType,
        part2: InputType
    ): (InputType, InputType) = instructions match
        case a :: b :: rest => divideInstructions(rest, a :: part1, b :: part2)
        case _              => (part1.reverse, part2.reverse)
end Day03

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2015_03(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2015, 3) match
        case Some(raw_input) =>
            printSolution(Day03(raw_input).solve)
        case _ => impossibleStateError
end run_2015_03
// */
