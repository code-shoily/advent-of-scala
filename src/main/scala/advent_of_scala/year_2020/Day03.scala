/** 2020/3: Toboggan Trajectory
  *
  * Link: https://adventofcode.com/2020/day/3
  *
  * Difficulty: xs
  *
  * Tags: vector
  *
  * Answers: (272L, 3898725600L)
  */
package advent_of_scala.year_2020

import advent_of_scala.base.Solution
import advent_of_scala.year_2020.Day03.*
import advent_of_scala.year_2020.TerrainObject.{Square, Tree}

import scala.annotation.tailrec

class Day03(rawInput: List[String]):
    private final val slopes = List((1, 1), (1, 3), (1, 5), (1, 7), (2, 1))

    def solve: Solution =
        val input = parsedInput
        val tobogganTravel = travel(input)
        val part1 = tobogganTravel(1, 3).toLong
        val part2 = slopes.foldLeft(1L) { (acc, x) => acc * tobogganTravel(x._1, x._2) }
        (part1, part2)
    end solve

    private def parsedInput: InputType =
        def generateTerrain(line: String) =
            line.toVector.map {
                case '#' => TerrainObject.Tree
                case '.' => TerrainObject.Square
            }

        rawInput.map(generateTerrain).toVector
    end parsedInput
end Day03

object Day03:
    type InputType = Vector[Vector[TerrainObject]]

    private def travel(terrain: Vector[Vector[TerrainObject]])(down: Int, right: Int): Int =
        val vLen = terrain.length
        val hLen = terrain.head.length

        @tailrec
        def doTravel(v: Int, h: Int, result: Int): Int =
            if v >= terrain.length then result
            else
                val (nextV, nextH) = (v + down, (h + right) % hLen)
                terrain(v)(h) match
                    case Tree   => doTravel(nextV, nextH, result + 1)
                    case Square => doTravel(nextV, nextH, result)

        doTravel(0, 0, 0)
    end travel
end Day03

enum TerrainObject:
    case Square, Tree

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2020_03(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}

    readLines(2020, 3) match
        case Some(raw_input) =>
            printSolution(Day03(raw_input).solve)
        case _ => impossibleStateError
end run_2020_03
// */
