/** 2016/22: Grid Computing
  *
  * Link: https://adventofcode.com/2016/day/22
  *
  * Difficulty: l
  *
  * Tags: ssp astar combinatorics revisit
  *
  * Answers: (955, 246)
  */
package advent_of_scala.year_2016

import scala.util.chaining.*

import advent_of_scala.base.Solution
import Day22.*
import Point.*

class Day22(rawInput: List[String]):
    def solvePart1(input: InputType): Int =
        input.toSeq.combinations(2).count { case Seq((_, first), (_, second)) =>
            (!first.empty && second.fit(first)) || (!second.empty && first.fit(second))
        }
    def solvePart2: Map[Point, Node] => Int = aStar compose initial

    def solve: Solution =
        val input = parsedInput
        (solvePart1(input), solvePart2(input))
    end solve

    private def parsedInput: InputType =
        rawInput.drop(2).map {
            _.split("\\D+").tail map { _.toInt } pipe { case Array(x, y, _, used, avail, _) =>
                Point(x, y) -> Node(used, avail)
            }
        }.toMap
end Day22

object Day22:
    type InputType = Map[Point, Node]
    private val orthogonal = Seq((1, 0), (-1, 0), (0, 1), (0, -1))

    case class Point(x: Int, y: Int):
        def adjacent: Seq[Point] = orthogonal.map(delta)
        def delta(dx: Int, dy: Int): Point = Point(x + dx, y + dy)
        def manhattan(other: Point): Int = (x - other.x).abs + (y - other.y).abs
    end Point

    case class Node(used: Int, avail: Int):
        def empty: Boolean = used == 0
        def fit(other: Node): Boolean = other.used <= avail
        def clear: Node = Node(0, avail + used)
        def merge(other: Node): Node = Node(used + other.used, avail - other.used)
    end Node

    case class State(goal: Point, hole: Point, grid: Map[Point, Node]):
        def hash: (Point, Point) = (goal, hole)
        def finished: Boolean = goal == Point(0, 0)
        def heuristic: Int = 1000 * goal.manhattan(Point(0, 0)) + goal.manhattan(hole)
    end State

    def swap(grid: Map[Point, Node], src: Point, dest: Point): Map[Point, Node] =
        grid.updated(src, grid(src).clear).updated(dest, grid(dest).merge(grid(src)))

    private def initial(grid: Map[Point, Node]): Seq[State] =
        val goal = Point(grid.keys.map(_.x).max, 0)
        val pairs = grid.keys.toSeq.flatMap { src =>
            val node = grid(src)
            src.adjacent.filter(grid.contains).filter(grid(_).fit(node)).map(dest => src -> dest)
        }
        pairs.map((src, dest) => State(goal, src, swap(grid, src, dest)))
    end initial

    def permutations(state: State): Seq[State] =
        val State(goal, hole, grid) = state
        hole.adjacent
            .filter(grid.contains)
            .filter(next => grid(hole).fit(grid(next)))
            .map { nextHole =>
                val nextGoal = if goal == nextHole then hole else goal
                State(nextGoal, nextHole, swap(grid, nextHole, hole))
            }
    end permutations

    private def aStar(initial: Seq[State]): Int =
        val cost = collection.mutable.Map.from(initial.map(s => s.hash -> 1))
        val todo = collection.mutable.PriorityQueue.from(initial.map(s => s -> s.heuristic))(
          Ordering.by(-_._2)
        )

        while todo.nonEmpty do
            val (current, _) = todo.dequeue()
            if current.finished then return cost(current.hash)
            val nextCost = cost(current.hash) + 1

            permutations(current).foreach { next =>
                if !cost.contains(next.hash) || nextCost < cost(next.hash) then
                    cost(next.hash) = nextCost
                    val priority = nextCost + next.heuristic
                    todo.enqueue(next -> priority)
            }
        end while

        -1
    end aStar
end Day22

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2016_22(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2016, 22) match
        case Some(raw_input) =>
            printSolution(Day22(raw_input).solve)
        case _ => impossibleStateError
end run_2016_22
// */
