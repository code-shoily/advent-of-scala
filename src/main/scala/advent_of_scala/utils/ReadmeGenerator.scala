package advent_of_scala.utils

import java.nio.file.{Files, Paths}
import scala.io.Source

final val lastYear = 2022
final val starIcon = ":star:"
final val trophy = ":trophy:"
final val trophy1 = ":1st_place_medal:"
final val trophy2 = ":2nd_place_medal:"

case class SolutionMeta(
    year: Int,
    day: Int,
    title: String,
    difficulty: String,
    tags: List[String],
    answers: String,
    mainLink: String,
    sourceLink: String,
    inputLink: String,
    testLink: String
):
    def isPartial: Boolean = answers.endsWith("!")
    def statusIcon: String = if isPartial then trophy2 else trophy1
    def difficultyIcon: String =
        val icon = s"$starIcon "
        difficulty match
            case "xs" => icon * 1
            case "s"  => icon * 2
            case "m"  => icon * 3
            case "l"  => icon * 4
            case "xl" => icon * 5
        end match
    end difficultyIcon

    def asMarkDownRow: String =
        s"""
       #|| $day | [$title]($mainLink) | $statusIcon | $difficultyIcon | [Day${"%02d".format(
            day
          )}.scala](/$sourceLink) | [${"%02d".format(
            day
          )}.txt](/$inputLink) | [Day${"%02d".format(
            day
          )}Suite.scala](/$testLink) | $answers | ${tags.mkString(",")} |""".stripMargin('#')

end SolutionMeta

object SolutionMeta:
    def title(using sourceCodeLines: List[String]): String =
        sourceCodeLines(0).stripPrefix("/**").strip

    def attrBy(attribute: String)(using sourceCodeLines: List[String]): String =
        sourceCodeLines.map(_.strip).find(_.startsWith(s"* $attribute:")).get.split(":")(1).strip()

    def getLinks(year: Int, day: Int): Map[String, String] =
        Map(
          "mainLink" -> s"https://adventofcode.com/$year/day/$day",
          "sourceLink" -> f"src/main/scala/advent_of_scala/$year/Day${day}%02d.scala",
          "testLink" -> f"src/test/scala/advent_of_scala/$year/Day${day}%02dSuite.scala",
          "inputLink" -> f"src/main/resources/inputs/$year/${day}%02d.txt"
        )

    def isPuzzleSolved(links: Map[String, String]): Boolean =
        val sourceLinkExists = Files.exists(Paths.get(links("sourceLink")))
        val testLinkExists = Files.exists(Paths.get(links("testLink")))
        val inputLinkExists = Files.exists(Paths.get(links("inputLink")))

        sourceLinkExists && testLinkExists && inputLinkExists
    end isPuzzleSolved

    def solutionMetaIfExists(year: Int, day: Int): Option[SolutionMeta] =
        val links = getLinks(year, day)

        if isPuzzleSolved(links) then
            given sourceCodeLines: List[String] =
                Source.fromFile(links("sourceLink")).getLines().toList

            val meta =
                SolutionMeta(
                  year = year,
                  day = day,
                  title = title,
                  difficulty = attrBy("Difficulty"),
                  tags = attrBy("Tags").split(" ").toList,
                  answers = attrBy("Answers"),
                  mainLink = links("mainLink"),
                  sourceLink = links("sourceLink"),
                  inputLink = links("inputLink"),
                  testLink = links("testLink")
                )
            Some(meta)
        else
            None
        end if
    end solutionMetaIfExists

    def solutionMetaForYear(year: Int): List[SolutionMeta] =
        val days = 1 to 25
        val metadata: List[SolutionMeta] = {
            for day <- days yield solutionMetaIfExists(year, day)
        }.filter(_.isDefined).map(_.get).toList

        metadata
    end solutionMetaForYear

    def starsCollected(metadata: List[SolutionMeta], year: Int): Int =
        2 * metadata.length - metadata.count(_.isPartial)

    def markDownPageHeaderForYear(metadata: List[SolutionMeta], year: Int): String =
        def yearFormat =
            (2015 to lastYear) map {
                case y if y == year => year.toString()
                case y              => s"[$y](/src/main/scala/advent_of_scala/$y/README.md)"
            } mkString (" | ")
        s"""
      |# Advent of Code $year
      |
      |[Main Page](https://adventofcode.com/$year)
      |
      |$yearFormat
      |
      |## $trophy ${starsCollected(metadata, year)}/50
      |""".stripMargin
    end markDownPageHeaderForYear

    def markDownTableHeader: String =
        """
      # || Day | Title | Status | Difficulty | Solution Page | Input | Test Page | Answer | Tags | 
      # || :---: | :------: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |""" stripMargin {
            '#'
        }

    def readmeContent(year: Int) =
        val metadata = solutionMetaForYear(year)

        s"""
      |${markDownPageHeaderForYear(metadata, year)}
      |${markDownTableHeader}${metadata map { _.asMarkDownRow } mkString ("")}
      |""".stripMargin
    end readmeContent

    def writeReadMeForYear(year: Int) =
        val content = readmeContent(year)
        val location = Paths.get(s"src/main/scala/advent_of_scala/$year/README.md")
        val newPath = Files.write(location, content.getBytes())

        s"[Success] Solution status updated the readme at $newPath"
    end writeReadMeForYear

    def trophy(year: Int, day: Int): String =
        solutionMetaIfExists(year, day) match
            case Some(metadata) => metadata.statusIcon
            case None           => ""

    def summaryTable =
        val header = "|:calendar:" + (2015 to 2022).map(year => s"[$year](/src/main/scala/advent_of_scala/$year)").mkString("|", "|", "|")
        val aligner = "|:-:| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |"
        val body =
            (1 to 25) map { day =>
                s"|$day" + (2015 to 2022).map(trophy(_, day)).mkString("|", "|", "|")
            } mkString ("\n")
        val trophies =
            (1 to 25).map(day =>
                (2015 to 2022).map({
                    trophy(_, day) match
                        case `trophy1` => 2
                        case `trophy2` => 1
                        case _         => 0
                }).sum
            ).sum
        val table = s"$header\n$aligner\n$body"

        (trophies, table)
    end summaryTable

    def writeReadme() =
        val (trophy, table) = summaryTable
        val content = Source.fromResource(
          "templates/README_TEMPLATE.md"
        ).getLines().toList.mkString("\n").format(trophy, table)

        Files.write(Paths.get("README.md"), content.getBytes())
    end writeReadme
end SolutionMeta
