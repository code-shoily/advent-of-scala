/** 2021/3: Binary Diagnostic
  *
  * Link: https://adventofcode.com/2021/day/3
  *
  * Difficulty: xs
  *
  * Tags: vector mutation number-system
  *
  * Answers: (1_540_244, 4_203_981)
  */
package advent_of_scala.year_2021

import advent_of_scala.base.{Solution, impossibleStateError}

type InputType3 = List[Array[Int]]

def asBin(binArray: IndexedSeq[Int], size: Int): Int =
    binArray.zipWithIndex.map { case (v, i) => v * Math.pow(2, size - i - 1) }.sum.toInt

class Day03(rawInput: List[String]):
    val size = rawInput.head.length()

    def solvePart1(input: InputType3): Int = bitToInt(getBitVector(input), size)
    def solvePart2(input: InputType3): Int =
        val o2 = scrubberRating(true)
        val co2 = scrubberRating(false)
        asBin(o2(input, 0), size) * asBin(co2(input, 0), size)
    end solvePart2

    def solve: Solution =
        val input = parsedInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    private def parsedInput: InputType3 = rawInput.map(_.split("").map(_.toInt))
end Day03

def getBitVector(lines: List[Array[Int]]): Vector[Bits] =
    val bitVector: Vector[Bits] = Vector.from(for _ <- (1 to lines.head.length) yield Bits(0, 0))
    lines.foreach(_.zipWithIndex.foreach { case (bit, idx) => bitVector(idx).update(bit) })
    bitVector
end getBitVector

case class Bits(var oneCount: Int, var zeroCount: Int):
    def update(bit: Int) = bit match
        case 1 => oneCount += 1
        case 0 => zeroCount += 1
    def mostCommon = if oneCount >= zeroCount then 1 else 0
    def leastCommon = if oneCount >= zeroCount then 0 else 1
end Bits

def bitToInt(bits: Vector[Bits], n: Int): Int =
    val gamma = asBin((0 until n).map(bits(_).mostCommon), n)
    val epsilon = asBin((0 until n).map(bits(_).leastCommon), n)
    gamma * epsilon
end bitToInt

def frequenciesAt(bitVector: Vector[Bits], idx: Int): (Int, Int) =
    (bitVector(idx).mostCommon, bitVector(idx).leastCommon)

def scrubberRating(isO2: Boolean)(rawBits: List[Array[Int]], idx: Int): Array[Int] = rawBits match
    case done if rawBits.length == 1 => done.head
    case interim =>
        val bitVector = getBitVector(interim)
        val compareWith = frequenciesAt(bitVector, idx)(if isO2 then 0 else 1)
        scrubberRating(isO2)(rawBits.filter(_(idx) == compareWith), idx + 1 % rawBits.head.length)

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2021_03 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2021, 3) match
        case Some(raw_input) =>
            printSolution(Day03(raw_input).solve)
        case _ => impossibleStateError
end run_2021_03
// */
