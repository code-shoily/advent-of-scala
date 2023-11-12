/** 2022/4: ???
  *
  * Link: https://adventofcode.com/2022/day/4
  *
  * Difficulty: xs
  *
  * Tags: range
  *
  * Answers: (518, 909)
  */
package advent_of_scala.year_2022

import advent_of_scala.base.{Solution, impossibleStateError}

type InputType4 = List[((Int, Int), (Int, Int))]

class Day04(rawInput: List[String]):
    def solvePart1(input: InputType4): Int = input.count(fullyContains)
    def solvePart2(input: InputType4): Int = input.count(overlaps)

    def solve: Solution =
        val input = parseInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    private def parseInput: InputType4 =
        def toRange(rr: String): (Int, Int) =
            val Array(a, b) = rr split ("-") map (_.toInt)
            (a, b)
        rawInput map (_.split(",").toSeq) map { case Seq(a, b) =>
            (toRange(a), toRange(b))
        }
    end parseInput
end Day04

def fullyContains(a: (Int, Int), b: (Int, Int)): Boolean = (a, b) match
    case ((a1, b1), (a2, b2)) if a1 <= a2 && b1 >= b2 => true
    case ((a1, b1), (a2, b2)) if a2 <= a1 && b2 >= b1 => true
    case _                                            => false

def overlaps(a: (Int, Int), b: (Int, Int)): Boolean = (a, b) match
    case ((a1, b1), (a2, b2)) if b1 >= a2 && b1 <= b2 => true
    case ((a1, b1), (a2, b2)) if b2 >= a1 && b2 <= b1 => true
    case _                                            => false

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2022_04 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2022, 4) match
        case Some(raw_input) =>
            printSolution(Day04(raw_input).solve)
        case _ => impossibleStateError
end run_2022_04
// */
