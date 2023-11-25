/** 2015/4: The Ideal Stocking Stuffer
  *
  * Link: https://adventofcode.com/2015/day/4
  *
  * Difficulty: s
  *
  * Tags: md5 digest inline-input
  *
  * Answers: (254_575, 1_038_736)
  */
package advent_of_scala.year_2015

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

import advent_of_scala.base.{Solution, impossibleStateError}

class Day04(rawInput: List[String]):
    def solvePart1(input: String): Int = mine(input, "00000").get
    def solvePart2(input: String): Int = mine(input, "000000").get

    def solve: Solution =
        val input = parsedInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    private def parsedInput: String = rawInput.head
end Day04

val md = MessageDigest.getInstance("MD5")

def mine(input: String, prefix: String): Option[Int] =
    def toHexBinary(bytes: Array[Byte]): String =
        val hexArray = "0123456789ABCDEF".toCharArray
        val hexChars = new Array[Char](bytes.length * 2)
        for i <- bytes.indices do
            val v = bytes(i) & 0xff
            hexChars(i * 2) = hexArray(v >>> 4)
            hexChars(i * 2 + 1) = hexArray(v & 0x0f)
        new String(hexChars)
    end toHexBinary

    LazyList.from(1).find { i =>
        md.update((input + i).getBytes(StandardCharsets.UTF_8))
        toHexBinary(md.digest()) startsWith (prefix)
    }
end mine

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2015_04 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2015, 4) match
        case Some(raw_input) =>
            printSolution(Day04(raw_input).solve)
        case _ => impossibleStateError
end run_2015_04
// */
