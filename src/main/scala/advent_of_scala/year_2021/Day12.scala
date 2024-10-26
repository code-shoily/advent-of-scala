/** 2021/12: Passage Passing
  *
  * Link: https://adventofcode.com/2021/day/12
  *
  * Difficulty: m
  *
  * Tags: graph graph-traversal
  *
  * Answers: (4659, 148_962)
  */
package advent_of_scala.year_2021

import advent_of_scala.base.Solution
import advent_of_scala.year_2021.Day12.*

import scala.util.chaining.*

class Day12(rawInput: List[String]):
    def solve: Solution =
        given input: InputType = parsedInput
        (
          pathCount(_ => false, initialPath),
          pathCount(
            _ filterNot { _.head.isUpper } pipe { d => d.distinct.size == d.size },
            initialPath
          )
        )
    end solve

    private def parsedInput: InputType = rawInput.flatMap { case s"$l-$r" =>
        Seq(l -> r, r -> l)
    }.groupMap(_._1)(_._2)
end Day12

object Day12:
    type InputType = Map[String, List[String]]

    private final val initialPath = Seq("start")

    private def pathCount(pred: Seq[String] => Boolean, path: Seq[String])(using
        graph: InputType
    ): Int =
        path.head match
            case "end" => 1
            case cave => {
                    graph(cave) filter {
                        case "start" => false
                        case t       => t.head.isUpper || !path.contains(t) || pred(path)
                    } map { cave => pathCount(pred, cave +: path) }
                }.sum
end Day12

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2021_12(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2021, 12) match
        case Some(raw_input) =>
            printSolution(Day12(raw_input).solve)
        case _ => impossibleStateError
end run_2021_12
// */
