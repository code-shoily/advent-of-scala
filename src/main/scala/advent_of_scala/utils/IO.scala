package advent_of_scala.utils

import scala.io.StdIn.readInt
import scala.io.Source
import java.io.FileNotFoundException

import advent_of_scala.base.Solution

object IO:
    def consolePrompt: (Int, Int) =
        print("Enter year: ")
        val year = readInt()
        print("Enter day: ")
        val day = readInt()
        println()

        (year, day)
    end consolePrompt

    def readLines(year: Int, day: Int): Option[List[String]] =
        val path = f"inputs/$year/$day%02d.txt"
        try
            val source = Source.fromResource(path)
            Some(source.getLines().toList)
        catch case i: FileNotFoundException => None
    end readLines

    def printSolution(solution: Solution): Unit =
        val (part1, part2) = solution
        println(s"($part1, $part2)")

    def validateInput(year: Int, day: Int): Boolean =
        if year < 2015 || year > lastYear then false
        else if day < 1 || day >= 25 then false
        else true
end IO
