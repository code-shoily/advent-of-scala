/** 2021/2: Dive!
  *
  * Link: https://adventofcode.com/2021/day/2
  *
  * Difficulty: xs
  *
  * Tags: walk
  *
  * Answers: (1_660_158, 1_604_592_846)
  */
package advent_of_scala.year_2021

import advent_of_scala.base.{Solution, impossibleStateError}
import advent_of_scala.year_2021.Day02.*

class Day02(rawInput: List[String]):
    def solve: Solution =
        val input = parsedInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    def solvePart1(input: InputType): Int = solve(input, navigate)

    private def solve(input: InputType, navigationAction: (Position, Command) => Position): Int =
        input.foldLeft(Position(0, 0))(navigationAction).result

    def solvePart2(input: InputType): Int = solve(input, navigateWithAim)

    private def parsedInput: InputType = rawInput.map(createCommand)
end Day02

object Day02:
    type InputType = List[Command]

    private def navigate(currentPosition: Position, command: Command): Position =
        val Position(horizontal, depth, aim) = currentPosition

        command match
            case Command(Direction.Forward, x) => Position(horizontal + x, depth)
            case Command(Direction.Up, x)      => Position(horizontal, depth - x)
            case Command(Direction.Down, x)    => Position(horizontal, depth + x)
        end match
    end navigate

    private def navigateWithAim(currentPosition: Position, command: Command): Position =
        val Position(horizontal, depth, aim) = currentPosition

        command match
            case Command(Direction.Forward, x) => Position(horizontal + x, depth + aim * x, aim)
            case Command(Direction.Up, x)      => Position(horizontal, depth, aim - x)
            case Command(Direction.Down, x)    => Position(horizontal, depth, aim + x)
        end match
    end navigateWithAim

    private def createCommand(commandStr: String) =
        commandStr.split(" ") match
            case Array("forward", x) => Command(Direction.Forward, x.toInt)
            case Array("up", x)      => Command(Direction.Up, x.toInt)
            case Array("down", x)    => Command(Direction.Down, x.toInt)
            case _                   => impossibleStateError

    enum Direction:
        case Forward, Up, Down

    case class Command(direction: Direction, x: Int)

    case class Position(horizontal: Int, depth: Int, aim: Int = 0):
        def result: Int = horizontal * depth
end Day02

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2021_02(): Unit =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2021, 2) match
        case Some(raw_input) =>
            printSolution(Day02(raw_input).solve)
        case _ => impossibleStateError
end run_2021_02
// */
