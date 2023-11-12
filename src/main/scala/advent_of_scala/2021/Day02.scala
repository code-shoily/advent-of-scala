/** 2021/2: Dive!
  *
  * Link: https://adventofcode.com/2021/day/2
  *
  * Difficulty: xs
  *
  * Tags: pattern-matching
  *
  * Answers: (1_660_158, 1_604_592_846)
  */
package advent_of_scala.year_2021

import advent_of_scala.base.{Solution, impossibleStateError}

type InputType2 = List[Command]

class Day02(rawInput: List[String]):
    def solvePart1(input: InputType2): Int = solver(input, navigate)
    def solvePart2(input: InputType2): Int = solver(input, navigateWithAim)
    def solver(input: InputType2, navigationAction: (Position, Command) => Position) =
        input.foldLeft(Position(0, 0))(navigationAction).result

    def solve: Solution =
        val input = parseInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    private def parseInput: InputType2 = rawInput.map(createCommand(_))
end Day02

enum Direction:
    case Forward, Up, Down

case class Command(direction: Direction, x: Int)
case class Position(horizontal: Int, depth: Int, aim: Int = 0):
    def result = horizontal * depth

def navigate(currentPosition: Position, command: Command): Position =
    val Position(horizontal, depth, aim) = currentPosition

    command match
        case Command(Direction.Forward, x) => Position(horizontal + x, depth)
        case Command(Direction.Up, x)      => Position(horizontal, depth - x)
        case Command(Direction.Down, x)    => Position(horizontal, depth + x)
    end match
end navigate

def navigateWithAim(currentPosition: Position, command: Command): Position =
    val Position(horizontal, depth, aim) = currentPosition

    command match
        case Command(Direction.Forward, x) => Position(horizontal + x, depth + aim * x, aim)
        case Command(Direction.Up, x)      => Position(horizontal, depth, aim - x)
        case Command(Direction.Down, x)    => Position(horizontal, depth, aim + x)
    end match
end navigateWithAim

def createCommand(commandStr: String) =
    commandStr.split(" ") match
        case Array("forward", x) => Command(Direction.Forward, x.toInt)
        case Array("up", x)      => Command(Direction.Up, x.toInt)
        case Array("down", x)    => Command(Direction.Down, x.toInt)
        case _                   => impossibleStateError

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2021_02 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2021, 2) match
        case Some(raw_input) =>
            printSolution(Day02(raw_input).solve)
        case _ => impossibleStateError
end run_2021_02
// */
