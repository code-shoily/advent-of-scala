/** 2022/6: Tuning Trouble
  *
  * Link: https://adventofcode.com/2022/day/6
  *
  * Difficulty: xs
  *
  * Tags: sliding-window
  *
  * Answers: (1651, 3837)
  */
package advent_of_scala.year_2022

import advent_of_scala.base.Solution

class Day06(rawInput: List[String]):
    def solve: Solution =
        val input = parsedInput
        def findMarker(window: Int): Int =
            window + input.sliding(window).indexWhere(_.toSet.size == window)
        (findMarker(4), findMarker(14))
    end solve

    private def parsedInput = rawInput.head.toCharArray
end Day06

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2022_06(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2022, 6) match
        case Some(raw_input) =>
            printSolution(Day06(raw_input).solve)
        case _ => impossibleStateError
end run_2022_06
// */
