/** 2022/2: Rock, Paper, Scissors
  *
  * Link: https://adventofcode.com/2022/day/2
  *
  * Difficulty: xs
  *
  * Tags: lookup-table
  *
  * Answers: (12_645, 11_756)
  */
package advent_of_scala.year_2022

import advent_of_scala.base.Solution
import Day02.*

class Day02(rawInput: List[String]):
    def solvePart1(input: InputType): Int = input.foldLeft(0)((acc, x) => acc + computeScore(x))
    def solvePart2(input: InputType): Int = solvePart1(input.map(strategicRemap))

    def solve: Solution =
        val input = parsedInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    private def parsedInput: InputType = rawInput.map(strategyToItems)
end Day02

object Day02:
    type InputType = List[(Items, Items)]

    enum Items:
        case Rock, Paper, Scissor

    private def computeScore(pair: (Items, Items)) = pair match
        case (Items.Rock, Items.Rock)       => 4 // 1 + 3
        case (Items.Rock, Items.Paper)      => 8 // 2 + 6
        case (Items.Rock, Items.Scissor)    => 3 // 3 + 0
        case (Items.Paper, Items.Rock)      => 1 // 1 + 0
        case (Items.Paper, Items.Paper)     => 5 // 2 + 3
        case (Items.Paper, Items.Scissor)   => 9 // 3 + 6
        case (Items.Scissor, Items.Rock)    => 7 // 1 + 6
        case (Items.Scissor, Items.Paper)   => 2 // 2 + 0
        case (Items.Scissor, Items.Scissor) => 6 // 3 + 3

    private def strategicRemap(pair: (Items, Items)): (Items, Items) = pair match
        case (Items.Rock, Items.Rock)       => (Items.Rock, Items.Scissor) // Lose
        case (Items.Rock, Items.Paper)      => (Items.Rock, Items.Rock) // Lose
        case (Items.Rock, Items.Scissor)    => (Items.Rock, Items.Paper) // Lose
        case (Items.Paper, Items.Rock)      => (Items.Paper, Items.Rock) // Draw
        case (Items.Paper, Items.Paper)     => (Items.Paper, Items.Paper) // Draw
        case (Items.Paper, Items.Scissor)   => (Items.Paper, Items.Scissor) // Draw
        case (Items.Scissor, Items.Rock)    => (Items.Scissor, Items.Paper) // Win
        case (Items.Scissor, Items.Paper)   => (Items.Scissor, Items.Scissor) // Win
        case (Items.Scissor, Items.Scissor) => (Items.Scissor, Items.Rock) // Win

    private def strategyToItems(ss: String): (Items, Items) =
        val playToItem = (s: String) =>
            s match
                case "A" | "X" => Items.Rock
                case "B" | "Y" => Items.Paper
                case "C" | "Z" => Items.Scissor

        ss.strip().split(" ") match
            case Array(a, b) => (playToItem(a), playToItem(b))
    end strategyToItems
end Day02

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2022_02(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2022, 2) match
        case Some(raw_input) =>
            printSolution(Day02(raw_input).solve)
        case _ => impossibleStateError
end run_2022_02
// */
