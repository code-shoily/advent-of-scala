/** Day 1: Chronal Calibration
  *
  * Link: https://adventofcode.com/2018/day/1
  *
  * Difficulty: xs
  *
  * Tags: linked-list
  */
package year_2018

import base.Solution
import scala.annotation.tailrec

type InputType = List[Int]

class Day01(rawInput: List[String]):
  def solvePart1(input: InputType): Int = input.reduce(_ + _)
  def solvePart2(input: InputType): Int =
    val frequencyHistory = Set[Int]()

    @tailrec
    def recurse(
        frequencies: InputType,
        history: Set[Int],
        total: Int = 0
    ): Int = frequencies match
      case x :: rest if history.contains(x + total) => x + total
      case x :: rest => recurse(rest, history + (x + total), x + total)
      case Nil =>
        recurse(input, history, total)

    recurse(input, frequencyHistory)

  def solve(): Solution =
    val input = parseInput
    val part1 = solvePart1(input)
    val part2 = solvePart2(input)
    (part1, part2)

  def parseInput: InputType = rawInput.map(_.toInt).toList

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2018_01 =
  import utils.io.{readLines, printSolution}

  readLines(2018, 1) match
    case Some(rawInput) =>
      val solver = Day01(rawInput)
      printSolution(solver.solve())
    case _ => sys.error("Could not read file")
// */
