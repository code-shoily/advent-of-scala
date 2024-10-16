/** 2016/24: Air Duct Spelunking
  *
  * Link: https://adventofcode.com/2016/day/24
  *
  * Difficulty: m
  *
  * Tags: graph-traversal tsp
  *
  * Answers: (462, 676)
  */
package advent_of_scala.year_2016

import advent_of_scala.base.Solution
import Day24.*

class Day24(rawInput: List[String]):
    def solvePart1(input: InputType): Int =
        val paths = input.keys.toList.sorted.permutations.map(_.prepended(0))
        shortestPath(input, paths)

    def solvePart2(input: InputType): Int =
        val paths = input.keys.toList.sorted.permutations.map(_.prepended(0).appended(0))
        shortestPath(input, paths)

    def solve: Solution =
        val input = parsedInput
        (solvePart1(input), solvePart2(input))
    end solve

    def parsedInput: InputType =
        val (grid, pois) =
            val grid =
                for
                    x <- rawInput.head.indices
                    y <- rawInput.indices
                    if rawInput(y)(x) != '#'
                yield Node(x, y)

            val pois =
                for
                    x <- rawInput.head.indices
                    y <- rawInput.indices
                    if rawInput(y)(x).isDigit
                yield rawInput(y)(x).asDigit -> Node(x, y)

            (grid.toSet, pois.toMap)
        end val

        pois transform { (_, start) =>
            pois transform { (_, end) =>
                bfs(grid, start, end)
            }
        }
    end parsedInput
end Day24

object Day24:
    type InputType = Map[Int, Map[Int, Int]]

    case class Node(x: Int, y: Int):
        def neighbours: Seq[Node] = List((1, 0), (-1, 0), (0, 1), (0, -1)) map { (dx, dy) =>
            Node(x + dx, y + dy)
        }
    end Node

    def bfs(grid: Set[Node], start: Node, end: Node): Int =
        val cost = collection.mutable.Map(start -> 0)
        val todo = collection.mutable.Queue(start)

        while todo.nonEmpty do
            val current = todo.dequeue()
            if current == end then return cost(current)
            val nextCost = cost(current) + 1

            current.neighbours.filter(grid.contains).foreach { next =>
                if !cost.contains(next) || nextCost < cost(next) then
                    cost(next) = nextCost
                    todo.enqueue(next)
            }
        end while

        -1
    end bfs

    private def shortestPath(traversals: InputType, paths: Iterator[Seq[Int]]) =
        paths.map(
          _.sliding(2).map(next => traversals(next.head)(next.last)).sum
        ).min
end Day24

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2016_24(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2016, 24) match
        case Some(raw_input) =>
            printSolution(Day24(raw_input).solve)
        case _ => impossibleStateError
end run_2016_24
// */
