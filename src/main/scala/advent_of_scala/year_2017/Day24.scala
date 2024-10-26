/** 2017/24: Electromagnetic Moat
  *
  * Link: https://adventofcode.com/2017/day/24
  *
  * Difficulty: s
  *
  * Tags: backtrack
  *
  * Answers: (1656, 1642)
  */
package advent_of_scala.year_2017

import advent_of_scala.base.Solution
import advent_of_scala.year_2017.Day24.*

class Day24(rawInput: List[String]):
    def solve: Solution =
        val input = parsedInput
        val solverPart1 = build(Ordering.by(_._2))
        val solverPart2 = build(Ordering.by(identity))
        (solverPart1(input), solverPart2(input))
    end solve

    private def parsedInput: InputType =
        rawInput.toSet.map { line =>
            val Array(a, b) = line.split("/").map(_.toInt)
            Component(a, b, a + b)
        }
end Day24

object Day24:
    type InputType = Set[Component]

    private def build(ordering: Ordering[(Int, Int)])(input: InputType) =
        def doBuild(components: Set[Component], current: Int, depth: Int, total: Int): (Int, Int) =
            val candidates = components.filter(_.matches(current))
            val shortlist = candidates.find(_.passthrough).map(Set(_)).getOrElse(candidates)
            if shortlist.isEmpty then depth -> total
            else
                shortlist.map(next =>
                    doBuild(
                      components - next,
                      next.opposite(current),
                      depth + 1,
                      total + next.strength
                    )
                ).max(ordering)
            end if
        end doBuild

        doBuild(input, 0, 0, 0)._2
    end build

    case class Component(a: Int, b: Int, strength: Int):
        def matches(n: Int): Boolean = n == a || n == b
        def passthrough: Boolean = a == b
        def opposite(n: Int): Int = if n == a then b else a
    end Component
end Day24

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2017_24(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2017, 24) match
        case Some(raw_input) =>
            printSolution(Day24(raw_input).solve)
        case _ => impossibleStateError
end run_2017_24
// */
