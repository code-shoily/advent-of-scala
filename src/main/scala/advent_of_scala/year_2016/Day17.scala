/** 2016/17: Two Steps Forward
  *
  * Link: https://adventofcode.com/2016/day/17
  *
  * Difficulty: s
  *
  * Tags: graph-traversal md5 inline-input assymmetric-result
  *
  * Answers: ("DURLDRRDRD", 650)
  */
package advent_of_scala.year_2016

import advent_of_scala.base.Solution
import advent_of_scala.year_2016.Day17.*

import java.security.MessageDigest
import scala.annotation.targetName
import scala.util.chaining.*

class Day17(rawInput: List[String]):
    def solve: Solution = rawInput.head pipe dfs

object Day17:
    val md5: MessageDigest = MessageDigest.getInstance("MD5")
    val directions: Map[Char, Point] =
        Map(
          'U' -> Point(0, -1),
          'D' -> Point(0, 1),
          'L' -> Point(-1, 0),
          'R' -> Point(1, 0)
        )

    private def dfs(passcode: String): (String, Int) =
        def dfsUtil(state: Room): Set[String] =
            if state.location == Point(3, 3) then Set(state.path)
            else state.neighbours(passcode).flatMap(dfsUtil)

        val paths = dfsUtil(Room(Point(0, 0), ""))

        (paths.minBy(_.length), paths.map(_.length).max)
    end dfs

    case class Point(x: Int, y: Int):
        @targetName("add")
        def +(other: Point): Point = Point(x + other.x, y + other.y)
        def isValid: Boolean = 0 <= x && x < 4 && 0 <= y && y < 4
    end Point

    case class Room(location: Point, path: String):
        def neighbours(passcode: String): Set[Room] = directions
            .map((next, move) => Room(location + move, path + next))
            .zip(hash(passcode + path))
            .filter((state, char) => state.location.isValid && char > 'a')
            .map(_._1)
            .toSet

        def hash(string: String): String =
            md5.digest(string.getBytes) take 2 map ("%02x".format(_)) mkString ""
    end Room
end Day17

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2016_17(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2016, 17) match
        case Some(raw_input) =>
            printSolution(Day17(raw_input).solve)
        case _ => impossibleStateError
end run_2016_17
// */
