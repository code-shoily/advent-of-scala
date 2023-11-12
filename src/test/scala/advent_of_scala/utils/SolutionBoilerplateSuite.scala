package advent_of_scala.tests.utils

import java.nio.file.Paths

import advent_of_scala.utils.SolutionBoilerplate

class SolutionBoilerplateSuite extends munit.FunSuite:
    val boilerplateForSolved = SolutionBoilerplate(2015, 1)
    val boilerplate = SolutionBoilerplate(2014, 1)

    test("Source file metadata does not generate for existing puzzle") {
        assert(boilerplateForSolved.createSourceStub.isEmpty)
    }

    test("Test file metadata does not generate for existing puzzle") {
        assert(boilerplateForSolved.createTestStub.isEmpty)
    }

    test("Source file metadata does not generate for existing puzzle") {
        assert(boilerplateForSolved.createInputStub.isEmpty)
    }

    test("Source file metadata correctly generates") {
        val sourcePath = "src/main/scala/advent_of_scala/2014/Day01.scala"
        val sourceContent =
            """
            |/** 2014/1: ???
            |  *
            |  * Link: https://adventofcode.com/2014/day/1
            |  *
            |  * Difficulty: 
            |  *
            |  * Tags:
            |  *
            |  * Answers: (???, ???)
            |  */
            |package advent_of_scala.year_2014
            |
            |import advent_of_scala.base.{Solution, impossibleStateError}
            |
            |type InputType = List[String]
            |
            |class Day01(rawInput: List[String]):
            |    def solvePart1(input: InputType): Int = ???
            |    def solvePart2(input: InputType): Int = ???
            |
            |    def solve: Solution =
            |        val input = parseInput
            |        val part1 = solvePart1(input)
            |        val part2 = solvePart2(input)
            |        (part1, part2)
            |    end solve
            |
            |    private def parseInput: InputType = rawInput
            |end Day01
            |
            |/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
            |@main def run_2014_01 =
            |    import advent_of_scala.utils.IO.{readLines, printSolution}
            |    readLines(2014, 1) match
            |        case Some(raw_input) =>
            |            printSolution(Day01(raw_input).solve)
            |        case _ => impossibleStateError
            |end run_2014_01
            |// */
            |""".stripMargin
        assertEquals(boilerplate.createSourceStub, Some((Paths.get(sourcePath), sourceContent)))
    }

    test("Test file metadata correctly generates") {
        val testPath = "src/test/scala/advent_of_scala/2014/Day01Suite.scala"
        val testContent =
            """
            |package advent_of_scala.tests.year_2014
            |
            |import advent_of_scala.utils.IO.readLines
            |import advent_of_scala.year_2014.Day01
            |
            |class Day01Suite extends munit.FunSuite:
            |    val year = 2014
            |    val day = 1
            |    val result = ???
            |
            |    test("Day 1 solve yields correct result") {
            |        val rawInput = readLines(year, day).get
            |        val solution = Day01(rawInput).solve
            |
            |        assertEquals(solution, result)
            |    }
            |end Day01Suite
            |""".stripMargin
        assertEquals(boilerplate.createTestStub, Some((Paths.get(testPath), testContent)))
    }

    test("Input file metadata correctly generates") {
        val inputPath = "src/main/resources/inputs/2014/01.txt"
        val inputContent = ""
        assertEquals(boilerplate.createInputStub, Some((Paths.get(inputPath), inputContent)))
    }

end SolutionBoilerplateSuite
