/** 2017/21: Fractal Art
  *
  * Link: https://adventofcode.com/2017/day/21
  *
  * Difficulty: m
  *
  * Tags: combinatorics matrix revisit
  *
  * Answers: (133, 2_221_990)
  */
package advent_of_scala.year_2017

import advent_of_scala.base.Solution

type InputType21 = Map[Seq[String], Seq[String]]

class Day21(rawInput: List[String]):
    def solve: Solution =
        val input = parsedInput
        val solverPart1 = fractal(5)
        val solverPart2 = fractal(18)
        (solverPart1(input), solverPart2(input))
    end solve

    private def parsedInput: InputType21 =
        def permutations(pattern: Seq[String]) =
            val rotated = Iterator.iterate(pattern, 4)(_.reverse.transpose.map(_.mkString)).toSeq
            rotated ++ rotated.map(_.reverse)

        rawInput.flatMap { line =>
            val Array(first, second) = line.split(" => ")
            val pattern = first.split("/").toSeq
            val replacement = second.split("/").toSeq
            permutations(pattern).map(_ -> replacement)
        }.toMap
    end parsedInput

    def fractal(n: Int)(rules: InputType21): Int =
        Iterator.iterate(Seq(".#.", "..#", "###")) { pattern =>
            val size = if pattern.size % 2 == 0 then 2 else 3
            pattern
                .map(_.grouped(size).toSeq)
                .grouped(size)
                .toSeq
                .flatMap { _.transpose.map(rules).transpose.map(_.mkString) }
        }.drop(n).next().map(_.count(_ == '#')).sum
    end fractal
end Day21

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2017_21 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    import advent_of_scala.base.impossibleStateError
    readLines(2017, 21) match
        case Some(raw_input) =>
            printSolution(Day21(raw_input).solve)
        case _ => impossibleStateError
end run_2017_21
// */
