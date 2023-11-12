/** 2015/5: Doesn't He Have Intern-Elves For This?
  *
  * Link: https://adventofcode.com/2015/day/5
  *
  * Difficulty: xs
  *
  * Tags: string 
  *
  * Answers: (255, 55)
  */
package advent_of_scala.year_2015

import advent_of_scala.base.{Solution, impossibleStateError}
import scala.annotation.tailrec

type InputType5 = List[List[Char]]

class Day05(rawInput: List[String]):
    def solvePart1(input: InputType5): Int =
        def isNice(chars: List[Char]) = noDQ(chars) && threeVowels(chars) && dupes(chars)
        input count (isNice)

    def solvePart2(input: InputType5): Int =
        def isNice(chars: List[Char]) =
            repeatingPairs(chars, Set[(Char, Char)](), None) && sandwitched(chars)
        input count (isNice)
    end solvePart2

    def solve: Solution =
        val input = parseInput
        val part1 = solvePart1(input)
        val part2 = solvePart2(input)
        (part1, part2)
    end solve

    private def parseInput: InputType5 = rawInput.map(_.toList)
end Day05

val vowels = List('a', 'e', 'i', 'o', 'u')
val disqualifiers = Set[(Char, Char)](('a', 'b'), ('c', 'd'), ('p', 'q'), ('x', 'y'))

@tailrec
final def noDQ(chars: List[Char]): Boolean = chars match
    case a :: b :: next if disqualifiers.contains((a, b)) => false
    case _ :: next                                        => noDQ(next)
    case Nil                                              => true

@tailrec
final def threeVowels(chars: List[Char], count: Int = 0): Boolean =
    if count == 3 then
        true
    else
        chars match
            case head :: next if vowels.contains(head) => threeVowels(next, count + 1)
            case head :: next                          => threeVowels(next, count)
            case Nil                                   => false

@tailrec
final def dupes(chars: List[Char]): Boolean = chars match
    case a :: b :: next if a == b => true
    case _ :: next                => dupes(next)
    case Nil                      => false

@tailrec
final def repeatingPairs(
    chars: List[Char],
    history: Set[(Char, Char)],
    lastMatch: Option[(Char, Char)]
): Boolean = chars match
    case a :: b :: rest if history.contains((a, b)) =>
        lastMatch match
            case Some(pair) if pair == (a, b) => repeatingPairs(b :: rest, history, None)
            case _                            => true

    case a :: b :: rest =>
        val pair = (a, b)
        repeatingPairs(b :: rest, history + pair, Some(pair))
    case _ => false

@tailrec
final def sandwitched(chars: List[Char]): Boolean = chars match
    case a :: b :: c :: rest if a == c => true
    case _ :: rest                     => sandwitched(rest)
    case Nil                           => false

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2015_05 =
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2015, 5) match
        case Some(raw_input) =>
            printSolution(Day05(raw_input).solve)
        case _ => impossibleStateError
end run_2015_05
// */
