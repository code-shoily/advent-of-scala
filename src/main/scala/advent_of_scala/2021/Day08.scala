/** 2021/8: Seven Segment Search
  *
  * Link: https://adventofcode.com/2021/day/8
  *
  * Difficulty: m
  *
  * Tags: lookup-table revisit
  *
  * Answers: (534, 1_070_188)
  */
package advent_of_scala.year_2021

import advent_of_scala.base.Solution

import Day08.*
import Digit.*
import Segment.*

class Day08(rawInput: List[String]):
    def solvePart1(input: List[String]): Int = {
        for
            display <- input map { _.split('|')(1).trim }
            segments <- display split (" ")
            uniqueDigit <- lookupUnique(Segment toSegments (segments))
        yield uniqueDigit
    }.size

    def solvePart2(input: List[String]): Int =
        def splitParts(line: String): (Seq[Segments], Seq[Segments]) =
            line.split('|') map {
                _.trim.split(" ").toSeq map (Segment.toSegments)
            } match
                case Array(cipher, plaintext) => (cipher, plaintext)

        input map (splitParts) map { (cipher, plaintext) =>
            plaintext.map(subsTable(cipher))
        } map { _.foldLeft(0)((acc, d) => acc * 10 + d.ordinal) } reduce (_ + _)
    end solvePart2

    def solve: Solution =
        val part1 = solvePart1(rawInput)
        val part2 = solvePart2(rawInput)
        (part1, part2)
    end solve
end Day08

object Day08:
    type Segments = Set[Segment]

    enum Segment:
        case A, B, C, D, E, F, G

    object Segment:
        val charToSegment: Map[Char, Segment] = {
            values map { s => s.toString.head.toLower -> s }
        }.toMap

        def toSegments(s: String): Segments = { s map (charToSegment) }.toSet
    end Segment

    enum Digit(val segments: Segment*):
        case Zero extends Digit(A, B, C, E, F, G)
        case One extends Digit(C, F)
        case Two extends Digit(A, C, D, E, G)
        case Three extends Digit(A, C, D, F, G)
        case Four extends Digit(B, C, D, F)
        case Five extends Digit(A, B, D, F, G)
        case Six extends Digit(A, B, D, E, F, G)
        case Seven extends Digit(A, C, F)
        case Eight extends Digit(A, B, C, D, E, F, G)
        case Nine extends Digit(A, B, C, D, F, G)
    end Digit

    object Digit:
        val index: IndexedSeq[Digit] = values.toIndexedSeq

        val lookupTable: Map[Int, Digit] =
            index groupBy { _.segments.size } collect { case k -> Seq(d) => k -> d }

        def lookupUnique(segments: Segments): Option[Digit] =
            lookupTable get (segments.size)

    end Digit

    def subsTable(cipher: Seq[Segments]): Map[Segments, Digit] =
        def lookup(section: Seq[Segments], withSegments: Segments) =
            section.partition(withSegments.subsetOf) match
                case (Seq(uniqueMatch), remaining) => (uniqueMatch, remaining)

        val uniques: Map[Digit, Segments] =
            Map.from(
              for
                  segments <- cipher
                  digit <- lookupUnique(segments)
              yield digit -> segments
            )

        val ofSizeFive = cipher.filter(_.sizeIs == 5)
        val ofSizeSix = cipher.filter(_.sizeIs == 6)
        val one = uniques(One)
        val four = uniques(Four)
        val seven = uniques(Seven)
        val eight = uniques(Eight)
        val (three, remainingFives) = lookup(ofSizeFive, withSegments = one)
        val (nine, remainingSixes) = lookup(ofSizeSix, withSegments = three)
        val (zero, Seq(six)) = lookup(remainingSixes, withSegments = seven)
        val (five, Seq(two)) = lookup(remainingFives, withSegments = four &~ one)

        Seq(zero, one, two, three, four, five, six, seven, eight, nine).zip(Digit.index).toMap
    end subsTable
end Day08

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2021_08 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    import advent_of_scala.base.impossibleStateError
    readLines(2021, 8) match
        case Some(raw_input) =>
            printSolution(Day08(raw_input).solve)
        case _ => impossibleStateError
end run_2021_08
// */
