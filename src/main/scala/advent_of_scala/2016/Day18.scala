/** 2016/18: Like a Rogue
  *
  * Link: https://adventofcode.com/2016/day/18
  *
  * Difficulty: s
  *
  * Tags: bitwise bignum unfold
  *
  * Answers: (1951, 20_002_936)
  */
package advent_of_scala.year_2016

import advent_of_scala.base.Solution

class Day18(rawInput: List[String]):
    def solve: Solution =
        given input: String = rawInput.head
        (safe(40), safe(400_000))
    end solve

    def safe(count: Int)(using input: String): Int =
        val (width, start) =
            (input.length, BigInt(input map { c => if c == '^' then '1' else '0' }, 2))
        Iterator.iterate(start) { n =>
            (n << 1).clearBit(width) ^ (n >> 1)
        }.map(width - _.bitCount).take(count).sum
    end safe
end Day18

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2016_18 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    import advent_of_scala.base.impossibleStateError
    readLines(2016, 18) match
        case Some(raw_input) =>
            printSolution(Day18(raw_input).solve)
        case _ => impossibleStateError
end run_2016_18
// */
