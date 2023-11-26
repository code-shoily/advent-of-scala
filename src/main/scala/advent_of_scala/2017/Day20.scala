/** 2017/20: <TITLE>
  *
  * Link: https://adventofcode.com/2017/day/20
  *
  * Difficulty: s
  *
  * Tags: sequence query
  *
  * Answers: (300, 502)
  */
package advent_of_scala.year_2017

import scala.util.chaining.*

import advent_of_scala.base.Solution

import Day20.*

type InputType20 = Seq[Particle]

class Day20(rawInput: List[String]):
    def solvePart1(input: InputType20): Int =
        Iterator.iterate(input)(_.map(_.step))
            .drop(500)
            .next()
            .zipWithIndex
            .minBy(_._1.p.manhattan)
            ._2

    def solvePart2(input: InputType20): Int =
        Iterator.iterate(input)(
          _.map(_.step)
              .groupBy(_.p)
              .values
              .filter(_.size == 1)
              .flatten
              .toSeq
        ).drop(500).next().size

    def solve: Solution =
        val input = parsedInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    private def parsedInput: InputType20 = rawInput map {
        _.split("[^-\\d]+").tail map (_.toInt) pipe {
            case Array(sx, sy, sz, vx, vy, vz, ax, ay, az) =>
                Particle(Point(sx, sy, sz), Point(vx, vy, vz), Point(ax, ay, az))
        }
    }
end Day20

object Day20:
    case class Point(x: Int, y: Int, z: Int):
        def +(p1: Point): Point = Point(x + p1.x, y + p1.y, z + p1.z)
        def manhattan: Int = x.abs + y.abs + z.abs

    case class Particle(p: Point, v: Point, a: Point):
        def step = Particle(p + v + a, v + a, a)
end Day20

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2017_20 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    import advent_of_scala.base.impossibleStateError
    readLines(2017, 20) match
        case Some(raw_input) =>
            printSolution(Day20(raw_input).solve)
        case _ => impossibleStateError
end run_2017_20
// */
