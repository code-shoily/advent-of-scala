/** 2020/4: Passport Processing
  *
  * Link: https://adventofcode.com/2020/day/4
  *
  * Difficulty: xs
  *
  * Tags: predicates annoying
  *
  * Answers: (233, 111)
  */
package advent_of_scala.year_2020

import advent_of_scala.base.{Solution, impossibleStateError}
import advent_of_scala.year_2020.Day04.*

class Day04(rawInput: List[String]):

    def solve: Solution =
        val input = parsedInput
        val part1 = input.length
        val part2 = input map {
            case Some(data) => Passport.fromMap(data)
            case _          => impossibleStateError
        } count (_.isValid)
        (part1, part2)
    end solve

    private def parsedInput: InputType =
        rawInput.mkString("\n").split("\n\n").toList map validPassportMap filter { _.isDefined }
end Day04

object Day04:
    type InputType = List[Option[Map[String, String]]]

    case class Passport(
        byr: Int,
        iyr: Int,
        eyr: Int,
        hgt: (Int, String),
        hcl: String,
        ecl: String,
        pid: String
    ):
        def isValid: Boolean =
            isValidByr && isValidIyr && isValidEyr && isValidHgt && isValidHcl && isValidEcl && isValidPid
        private def isValidByr = isBetween(byr, 1920, 2002)
        private def isValidIyr = isBetween(iyr, 2010, 2020)
        private def isValidEyr = isBetween(eyr, 2020, 2030)
        private def isValidHgt = hgt match
            case (height, "in") => isBetween(height, 59, 76)
            case (height, "cm") => isBetween(height, 150, 193)
            case _              => false
        private def isValidHcl =
            hcl.startsWith("#") && hcl.length == 7 && (hcl.substring(
                1
            ).toCharArray.toSet subsetOf hexChars)
        private def isValidEcl = eyeColours contains ecl
        private def isValidPid = pid.length() == 9 && (pid.toCharArray.toSet subsetOf numChars)

    end Passport

    private object Passport:
        def fromMap(map: Map[String, String]): Passport =
            Passport(
              map("byr").toInt,
              map("iyr").toInt,
              map("eyr").toInt,
              heightTuple(map("hgt")),
              map("hcl"),
              map("ecl"),
              map("pid")
            )

        private def heightTuple(hgt: String) =
            if hgt.endsWith("in") then
                (hgt.stripSuffix("in").toInt, "in")
            else if hgt.endsWith("cm") then
                (hgt.stripSuffix("cm").toInt, "cm")
            else
                (0, "?")
    end Passport

    private final val numChars = ('0' to '9').toSet
    final val hexChars = ('a' to 'f').toSet ++ numChars
    private final val eyeColours = Set("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    private final val requiredFields = Set("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    private def isBetween(value: Int, a: Int, b: Int): Boolean = a <= value && value <= b
    private def validPassportMap(line: String) =
        val maybePassport =
            (line.split("\n").mkString(" ").split(" ") map { pair =>
                val Array(a, b) = pair.split(":")
                a -> b
            }).toMap

        if requiredFields subsetOf maybePassport.keySet then
            Some(maybePassport)
        else
            None
        end if
    end validPassportMap
end Day04

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2020_04(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2020, 4) match
        case Some(raw_input) =>
            printSolution(Day04(raw_input).solve)
        case _ => impossibleStateError
end run_2020_04
// */
