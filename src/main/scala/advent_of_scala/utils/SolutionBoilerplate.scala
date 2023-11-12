package advent_of_scala.utils

import java.nio.file.{Files, Path, Paths, StandardOpenOption}
import java.nio.charset.StandardCharsets

class SolutionBoilerplate(year: Int, day: Int):
    def create(): Option[String] =
        val source = createSourceStub
        val test = createTestStub
        val input = createInputStub

        if source.isDefined && test.isDefined && input.isDefined then
            val status =
                List(source, test, input).map { (template) =>
                    val (path, content) = template.get
                    val newPath = Files.write(path, content.getBytes)
                    s"[SUCCESS] Wrote to $newPath."
                }.mkString("\n")
            Some(status)
        else
            None
        end if
    end create

    def createSourceStub: Option[(Path, String)] =
        if !Files.exists(sourcePath) then Some((sourcePath, sourceContent)) else None

    def createTestStub: Option[(Path, String)] =
        if !Files.exists(testPath) then Some((testPath, testContent)) else None

    def createInputStub: Option[(Path, String)] =
        if !Files.exists(inputPath) then Some((inputPath, "")) else None

    private def sourceContent: String =
        s"""
        |/** Day $day: <TITLE HERE>
        |  *
        |  * Link: https://adventofcode.com/$year/day/$day
        |  *
        |  * Difficulty: 
        |  *
        |  * Tags:
        |  */
        |package advent_of_scala.year_$year
        |
        |import advent_of_scala.base.{Solution, impossibleStateError}
        |
        |type InputType = List[String]
        |
        |class Day${"%02d".format(day)}(rawInput: List[String]):
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
        |end Day${"%02d".format(day)}
        |
        |/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
        |@main def run_${"%02d".format(day)} =
        |    import advent_of_scala.utils.IO.{readLines, printSolution}
        |    readLines($year, $day) match
        |        case Some(raw_input) =>
        |            printSolution(Day${"%02d".format(day)}(raw_input).solve)
        |        case _ => impossibleStateError
        |end run_${year}_${"%02d".format(day)}
        |// */
        |
        """.stripMargin

    private def testContent: String =
        s"""
        |package advent_of_scala.tests.year_$year
        |
        |import advent_of_scala.utils.IO.readLines
        |import advent_of_scala.year_$year.Day${"%02d".format(day)}
        |
        |class Day${"%02d".format(day)}Suite extends munit.FunSuite:
        |    val year = $year
        |    val day = $day
        |    val result = ???
        |
        |    test("Day $day solve yields correct result") {
        |        val rawInput = readLines(year, day).get
        |        val solution = Day${"%02d".format(day)}(rawInput).solve
        |
        |        assertEquals(solution, result)
        |    }
        |end Day${"%02d".format(day)}Suite
        """.stripMargin

    def testPath: Path =
        Paths.get(s"src/test/scala/advent_of_scala/$year/Day${"%02d".format(day)}Suite.scala")
    def sourcePath: Path =
        Paths.get(s"src/main/scala/advent_of_scala/$year/Day${"%02d".format(day)}.scala")
    def inputPath: Path = Paths.get(s"src/main/resources/inputs/$year/${"%02d".format(day)}.txt")
end SolutionBoilerplate
