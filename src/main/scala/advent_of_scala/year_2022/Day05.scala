/** 2022/5: Supply Stacks
  *
  * Link: https://adventofcode.com/2022/day/5
  *
  * Difficulty: m
  *
  * Tags: parse-heavy stack linked-list string-result
  *
  * Answers: ("VPCDMSLWJ", "TPWCGNCCG")
  */
package advent_of_scala.year_2022

import advent_of_scala.base.Solution
import advent_of_scala.year_2022.Day05.*

class Day05(rawInput: List[String]):
    def solve: Solution =
        val input = parsedInput
        given containers: Containers = input._1
        given moves: Moves = input._2
        (getTopContainers(true), getTopContainers(false))
    end solve

    private def parsedInput =
        val input = rawInput.mkString("\n").split("\n\n")
        val movePattern = "move (\\d+) from (\\d+) to (\\d+)".r

        val moveSequence =
            input.tail.head.split("\n").map {
                case movePattern(quantity, source, destination) =>
                    Move(quantity.toInt, source.head, destination.head)
            }.toSeq

        val containerMap =
            val initialContainerMap = Map.from((1 to 9) map { (i: Int) => (i + '0').toChar } map {
                (_, List[Char]())
            })
            val idxMap: String => Map[Int, Char] =
                _.zipWithIndex.filterNot(_._1 == '_').map(_.swap).toMap

            val containers =
                input.head.split("\n").map(_.map(c =>
                    Map(']' -> '_', '[' -> '_', ' ' -> '_').getOrElse(c, c)
                )).toList.reverse
            val numberMap = idxMap(containers.head)

            containers.tail.foldLeft(initialContainerMap) { (acc, line) =>
                val newMap =
                    for
                        (idx, char) <- idxMap(line)
                    yield
                        val targetIdx = numberMap(idx)
                        targetIdx -> (char :: acc(targetIdx))

                acc ++ newMap
            }
        end containerMap

        (containerMap, moveSequence)
    end parsedInput
end Day05

object Day05:
    private type Containers = Map[Char, List[Char]]
    private type Moves = Seq[Move]

    case class Move(quantity: Int, source: Char, destination: Char):
        def moveContainers(shouldReverse: Boolean)(containers: Containers): Map[Char, List[Char]] =
            val (containersToMove, remainingContainers) = containers(source).splitAt(quantity)
            val destinationContainers =
                (if shouldReverse then containersToMove.reverse
                 else containersToMove) ++ containers(
                  destination
                )

            containers + (destination -> destinationContainers) + (source -> remainingContainers)
        end moveContainers
    end Move

    private def getTopContainers(shouldReverse: Boolean)(using
        containers: Containers,
        moves: Moves
    ) =
        moves.foldLeft(containers) { (acc, move) =>
            move.moveContainers(shouldReverse)(acc)
        }.toList.sortBy(_._1).map(_._2.head).mkString
end Day05

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2022_05(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2022, 5) match
        case Some(raw_input) =>
            printSolution(Day05(raw_input).solve)
        case _ => impossibleStateError
end run_2022_05
// */
