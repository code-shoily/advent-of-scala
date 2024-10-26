/** 2016/11: Radioisotope Thermoelectric Generators
  *
  * Link: https://adventofcode.com/2016/day/11
  *
  * Difficulty: l
  *
  * Tags: graph-traversal revisit
  *
  * Answers: (37, 61)
  */
package advent_of_scala.year_2016

import advent_of_scala.base.Solution
import advent_of_scala.year_2016.Day11.*

import scala.annotation.targetName

class Day11(rawInput: List[String]):
    def solvePart1(input: InputType): Int = bfs(input)
    def solvePart2(input: InputType): Int =
        val State(elevator, floors) = input
        val expanded = State(elevator, floors.updated(0, floors.head + Floor(2, 2)))
        bfs(expanded)

    def solve: Solution =
        val input = parsedInput
        (solvePart1(input), solvePart2(input))
    end solve

    private def parsedInput: InputType =
        val microchip = "microchip".r
        val generator = "generator".r
        State(
          0,
          rawInput map { line =>
              Floor(microchip.findAllIn(line).size, generator.findAllIn(line).size)
          }
        )
    end parsedInput
end Day11

object Day11:
    type InputType = State
    val permutations: Seq[Floor] =
        Seq(Floor(2, 0), Floor(1, 0), Floor(1, 1), Floor(0, 1), Floor(0, 2))
    val adjacent: Map[Int, Seq[Int]] = Map(0 -> Seq(1), 1 -> Seq(0, 2), 2 -> Seq(1, 3), 3 -> Seq(2))

    case class Floor(microchips: Int, generators: Int):
        def empty: Boolean = microchips == 0 && generators == 0
        def valid: Boolean =
            (microchips >= 0 && generators >= 0) && (generators == 0 || microchips <= generators)
        @targetName("plus")
        def +(other: Floor): Floor =
            Floor(microchips + other.microchips, generators + other.generators)
        @targetName("minus")
        def -(other: Floor): Floor =
            Floor(microchips - other.microchips, generators - other.generators)
    end Floor

    case class State(elevator: Int, floors: Seq[Floor]):
        def finished: Boolean = elevator == 3 && floors.take(3).forall(_.empty)
        def valid: Boolean = floors.forall(_.valid)
        def candidates: Seq[State] =
            for
                adjust <- permutations
                destination <- adjacent(elevator)
            yield State(
              destination,
              floors.updated(elevator, floors(elevator) - adjust).updated(
                destination,
                floors(destination) + adjust
              )
            )
    end State

    def bfs(start: State): Int =
        val todo = collection.mutable.Queue(start)
        val cache = collection.mutable.Map(start -> 0)

        while todo.nonEmpty do
            val current = todo.dequeue()
            val cost = cache(current) + 1
            current.candidates.filter(_.valid).foreach { next =>
                if !cache.contains(next) || cost < cache(next) then
                    cache(next) = cost
                    todo.enqueue(next)
            }
        end while

        cache(cache.keys.filter(_.finished).head)
    end bfs
end Day11

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2016_11(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2016, 11) match
        case Some(raw_input) =>
            printSolution(Day11(raw_input).solve)
        case _ => impossibleStateError
end run_2016_11
// */
