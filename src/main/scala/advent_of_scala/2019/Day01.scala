/** Day 1: Sonar Sweep
  *
  * Link: https://adventofcode.com/2019/day/1
  *
  * Difficulty: xs
  *
  * Tags: recursion reduce
  */
package advent_of_scala.year_2019

import advent_of_scala.base.Solution
import scala.annotation.tailrec

type InputType = Seq[Int]

class Day01(rawInput: List[String]):
    def solvePart1(input: InputType): Int =
        input.map(_ / 3 - 2).sum

    def solvePart2(input: InputType): Int =
        @tailrec
        def getFuel(mass: Int, fuel: Int = 0): Int =
            (mass / 3 - 2) match
                case notFuel if notFuel <= 0 => fuel
                case fuelPart                => getFuel(fuelPart, fuelPart + fuel)

        input.map(getFuel(_)).sum
    end solvePart2

    def solve: Solution =
        val input = parseInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    def parseInput: InputType = rawInput.map(_.toInt)
end Day01

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2019_01 =
    import advent_of_scala.utils.IO.{readLines, printSolution}

    readLines(2019, 1) match
        case Some(rawInput) =>
            val solver = Day01(rawInput)
            printSolution(solver.solve)
        case _ => sys.error("Could not read file")
    end match
end run_2019_01
// */
