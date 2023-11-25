/** 2016/3: Squares With Three Sides
  *
  * Link: https://adventofcode.com/2016/day/3
  *
  * Difficulty: xs
  *
  * Tags: geometry transpose
  *
  * Answers: (993, 1849)
  */
package advent_of_scala.year_2016

import advent_of_scala.base.{Solution, impossibleStateError}

type InputType3 = List[(Int, Int, Int)]

class Day03(rawInput: List[String]):
    def solve: Solution =
        val input = parseInput
        (input count (isTriangle), getVerticalTriplets(input) count (isTriangle))
    end solve

    private def parseInput: InputType3 =
        rawInput map {
            _.trim().split("\\s+").toList.map(_.toInt) match
                case a :: b :: c :: Nil => (a, b, c)
                case _                  => impossibleStateError
        }
end Day03

def isTriangle(triplets: (Int, Int, Int)) =
    val (a, b, c) = triplets
    (a + b) > c && (b + c) > a && (c + a) > b

def getVerticalTriplets(triplets: List[(Int, Int, Int)]) =
    triplets grouped (3) flatMap {
        case (a1, b1, c1) :: (a2, b2, c2) :: (a3, b3, c3) :: Nil =>
            List((a1, a2, a3), (b1, b2, b3), (c1, c2, c3))
        case _ => impossibleStateError
    }

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2016_03 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    import advent_of_scala.base.impossibleStateError
    readLines(2016, 3) match
        case Some(raw_input) =>
            printSolution(Day03(raw_input).solve)
        case _ => impossibleStateError
end run_2016_03
// */
