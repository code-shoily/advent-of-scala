/** 2021/4: Giant Squid
  *
  * Link: https://adventofcode.com/2021/day/4
  *
  * Difficulty: m
  *
  * Tags: parse-heavy grid2D
  *
  * Answers: (11_774, 4495)
  */
package advent_of_scala.year_2021

import advent_of_scala.base.{Solution, impossibleStateError}
import advent_of_scala.year_2021.Day04.*

class Day04(rawInput: List[String]):
    def solve: Solution =
        val InputType(numbers, initialState) = parsedInput
        numbers.foldLeft(initialState) { (bingo, n) =>
            val newBoards = bingo.boards filterNot (_.hasWon) map (Board.pick(_, n))
            newBoards find (_.hasWon) match
                case Some(board) =>
                    val score = Some(board.score(n))
                    Bingo(newBoards, if bingo._2.isEmpty then score else bingo._2, score)
                case _ => Bingo(newBoards, bingo._2, bingo._3)
            end match
        }.result
    end solve

    private def parsedInput: InputType =
        val sections = rawInput.mkString("\n").split("\n\n").toSeq
        InputType(
          sections.head.split(",").map(_.toInt),
          Bingo(sections.tail.map(Board.fromString), None, None)
        )
    end parsedInput
end Day04

object Day04:
    case class InputType(numbers: Seq[Int], boards: Bingo)

    case class Board(
        mapping: Map[Int, (Int, Int)],
        rows: Vector[Int] = Vector.from[Int](Seq(0, 0, 0, 0, 0)),
        cols: Vector[Int] = Vector.from[Int](Seq(0, 0, 0, 0, 0)),
        hasWon: Boolean = false
    ):
        def score(winningNumber: Int): Int = winningNumber * mapping.keySet.sum
    end Board

    case class Bingo(boards: Seq[Board], firstScore: Option[Int], finalScore: Option[Int]):
        def result: (Int, Int) = (firstScore, finalScore) match
            case (Some(score1), Some(score2)) => (score1, score2)
            case _                            => impossibleStateError
    end Bingo

    private object Board:
        def fromString(values: String): Board = Board(toMapping(values))

        private def toMapping(block: String): Map[Int, (Int, Int)] =
            val table = block.split("\n").map(_.trim.split(" +"))
            (
              for
                  i <- 0 until 5
                  j <- 0 until 5
              yield table(i)(j).toInt -> (i, j)
            ).toMap
        end toMapping

        def pick: (Board, Int) => Board = { case (board @ Board(mapping, rows, cols, won), n) =>
            mapping.get(n) match
                case Some((row, col)) =>
                    val (newRow, newCol) = (occupy(rows, row), occupy(cols, col))
                    val winStatus = newRow(row) == 5 || newCol(col) == 5 || won
                    Board(mapping.removed(n), newRow, newCol, winStatus)
                case _ => board
        }

        private def occupy(tally: Vector[Int], at: Int): Vector[Int] =
            tally.updated(at, tally(at) + 1)
    end Board
end Day04

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2021_04(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2021, 4) match
        case Some(raw_input) =>
            printSolution(Day04(raw_input).solve)
        case _ => impossibleStateError
end run_2021_04
// */
