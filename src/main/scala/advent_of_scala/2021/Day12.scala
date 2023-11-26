/** 2021/12: Passage Passing
  *
  * Link: https://adventofcode.com/2021/day/12
  *
  * Difficulty: m
  *
  * Tags: graph dfs
  *
  * Answers: (4659, 148_962)
  */
package advent_of_scala.year_2021

import scala.util.chaining.*

import advent_of_scala.base.Solution

type InputType12 = Map[String, List[String]]
final val initialPath = Seq("start")

class Day12(rawInput: List[String]):
    def solve: Solution =
        given input: InputType12 = parsedInput
        (
          pathCount(_ => false, initialPath),
          pathCount(
            _ filterNot { _.head.isUpper } pipe { d => d.distinct.size == d.size },
            initialPath
          )
        )
    end solve

    private def parsedInput: InputType12 = rawInput.flatMap { case s"$l-$r" =>
        Seq(l -> r, r -> l)
    }.groupMap(_._1)(_._2)
end Day12

def pathCount(pred: Seq[String] => Boolean, path: Seq[String])(using graph: InputType12): Int =
    path.head match
        case "end" => 1
        case cave => {
                graph(cave) filter {
                    case "start" => false
                    case t       => (t.head.isUpper || !path.contains(t) || pred(path))
                } map { cave => pathCount(pred, cave +: path) }
            }.sum

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2021_12 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    import advent_of_scala.base.impossibleStateError
    readLines(2021, 12) match
        case Some(raw_input) =>
            printSolution(Day12(raw_input).solve)
        case _ => impossibleStateError
end run_2021_12
// */
