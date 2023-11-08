/** Day 1: Sonar Sweep
  *
  * Link: https://adventofcode.com/2021/day/1
  *
  * Difficulty: xs
  *
  * Tags: sequence
  */
package year_2021

import base.Solution

type InputType = Seq[Int]

class Day01(rawInput: List[String]):
  def solvePart1(input: InputType): Int = countIncrements(input, 1)
  def solvePart2(input: InputType) = countIncrements(input, 3)

  def solve(): Solution =
    val input = parseInput
    val part1 = solvePart1(input)
    val part2 = solvePart2(input)
    (part1, part2)

  def parseInput: Seq[Int] = rawInput.map(_.toInt)

  private def countIncrements(sigs: InputType, by: Int): Int =
    (0 until (sigs.length - by)).foldLeft(0)((incr, idx) =>
      incr + (if sigs(idx) < sigs(idx + by) then 1 else 0)
    )

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2021_01 =
  import utils.io.{readLines, printSolution}

  readLines(2021, 1) match
    case Some(rawInput) =>
      val solver = Day01(rawInput)
      printSolution(solver.solve())
    case _ => sys.error("Could not read file")
// */