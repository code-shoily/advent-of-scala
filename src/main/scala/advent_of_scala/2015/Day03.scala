/** 2015/3: Perfectly Spherical Houses in a Vacuum
  *
  * Link: https://adventofcode.com/2015/day/3
  *
  * Difficulty: xs
  *
  * Tags: navigation set
  *
  * Answers: (2081, 2341)
  */
package advent_of_scala.year_2015

import advent_of_scala.base.{Solution, impossibleStateError}
import scala.annotation.tailrec

type InputType3 = List[Direction]

class Day03(rawInput: List[String]):
    def solvePart1(input: InputType3): Int =
        val delivery = Delivery()
        delivery.followDirections(input)
        delivery.visitedHouses.size

    def solvePart2(input: InputType3): Int =
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

    private def parsedInput: InputType3 = rawInput.head.map(asDirection(_)).toList
end Day03

enum Direction:
    case Up, Down, Left, Right

def asDirection(d: Char) = d match
    case '^' => Direction.Up
    case 'v' => Direction.Down
    case '<' => Direction.Left
    case '>' => Direction.Right

case class House(x: Int, y: Int):
    def getNeighbour(xDiff: Int, yDiff: Int): House = House(x + xDiff, y + yDiff)

class Delivery:
    var house = House(0, 0)
    var visitedHouses = Set[House]() + house

    def followDirections(directions: InputType3) = directions.foreach(nextHouse)

    def nextHouse(direction: Direction) =
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

@tailrec
final def divideInstructions(
    instructions: InputType3,
    part1: InputType3,
    part2: InputType3
): (InputType3, InputType3) = instructions match
    case a :: b :: rest => divideInstructions(rest, a :: part1, b :: part2)
    case _              => (part1.reverse, part2.reverse)

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2015_03 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2015, 3) match
        case Some(raw_input) =>
            printSolution(Day03(raw_input).solve)
        case _ => impossibleStateError
end run_2015_03
// */
