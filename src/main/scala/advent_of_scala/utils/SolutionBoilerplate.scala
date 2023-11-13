package advent_of_scala.utils

import java.nio.file.{Files, Path, Paths, StandardOpenOption}
import java.nio.charset.StandardCharsets

import sttp.client4.quick.*

object RemoteDataFetcher:
    def fetchInputData(year: Int, day: Int): String =
        sys.env.getOrElse("COOKIE", "0") match
            case "0" => ""
            case cookie =>
                quickRequest.get(uri"https://adventofcode.com/$year/day/$day/input").cookie(
                  "session",
                  cookie
                ).send().body
        end match
    end fetchInputData

    def fetchTitle(year: Int, day: Int): String =
        quickRequest.get(uri"https://adventofcode.com/$year/day/$day").send().body

    @main def main(args: String*) =
        println(fetchTitle(2015, 1))

end RemoteDataFetcher

class SolutionBoilerplate(year: Int, day: Int, fetchFromRemote: Boolean = false):
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
        val inputContent =
            if fetchFromRemote then RemoteDataFetcher.fetchInputData(year, day) else ""
        if !Files.exists(inputPath) then Some((inputPath, inputContent)) else None
    end createInputStub

    private def sourceContent: String =
        f"""
        |/** $year/$day: ???
        |  *
        |  * Link: https://adventofcode.com/$year/day/$day
        |  *
        |  * Difficulty: 
        |  *
        |  * Tags:
        |  *
        |  * Answers: (???, ???)
        |  */
        |package advent_of_scala.year_$year
        |
        |import advent_of_scala.base.{Solution, impossibleStateError}
        |
        |type InputType$day = List[String]
        |
        |class Day${day}%02d(rawInput: List[String]):
        |    def solvePart1(input: InputType$day): Int = ???
        |    def solvePart2(input: InputType$day): Int = ???
        |
        |    def solve: Solution =
        |        val input = parseInput
        |        val part1 = solvePart1(input)
        |        val part2 = solvePart2(input)
        |        (part1, part2)
        |    end solve
        |
        |    private def parseInput: InputType$day = rawInput
        |end Day${day}%02d
        |
        |/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
        |@main def run_${year}_${day}%02d =
        |    import advent_of_scala.utils.IO.{readLines, printSolution}
        |    readLines($year, $day) match
        |        case Some(raw_input) =>
        |            printSolution(Day${day}%02d(raw_input).solve)
        |        case _ => impossibleStateError
        |end run_${year}_${day}%02d
        |// */
        |""".stripMargin

    private def testContent: String =
        f"""
        |package advent_of_scala.tests.year_$year
        |
        |import advent_of_scala.utils.IO.readLines
        |import advent_of_scala.year_$year.Day${day}%02d
        |
        |class Day${day}%02dSuite extends munit.FunSuite:
        |    val year = $year
        |    val day = $day
        |    val result = ???
        |
        |    test("Day $day solve yields correct result") {
        |        val rawInput = readLines(year, day).get
        |        val solution = Day${day}%02d(rawInput).solve
        |
        |        assertEquals(solution, result)
        |    }
        |end Day${day}%02dSuite
        |""".stripMargin

    def testPath: Path =
        Paths.get(f"src/test/scala/advent_of_scala/$year/Day${day}%02dSuite.scala")
    def sourcePath: Path =
        Paths.get(f"src/main/scala/advent_of_scala/$year/Day${day}%02d.scala")
    def inputPath: Path = Paths.get(f"src/main/resources/inputs/$year/${day}%02d.txt")
end SolutionBoilerplate
