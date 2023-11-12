package advent_of_scala.utils

import scala.io.StdIn.readInt

import scala.io.Source
import java.net.URL
import java.io.FileNotFoundException
import advent_of_scala.base.Solution

object Perf:
    def timed[T](block: => T): T =
        val before = System.currentTimeMillis
        val result = block
        val after = System.currentTimeMillis
        println("Elapsed time: " + (after - before) + "ms")
        result
    end timed
end Perf

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
        val path = s"inputs/${year}/${"%02d".format(day)}.txt"
        try
            val source = Source.fromResource(path)
            Some(source.getLines().toList)
        catch case i: FileNotFoundException => None
    end readLines

    def printSolution(solution: Solution) =
        val (part1, part2) = solution
        println(s"Part 1: $part1 \t Part 2: $part2")

    def validateInput(year: Int, day: Int): Boolean =
        if year < 2015 || year >= 2023 then false
        else if day < 1 || day >= 25 then false
        else true
end IO
