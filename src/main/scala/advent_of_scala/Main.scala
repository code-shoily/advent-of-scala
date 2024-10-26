package advent_of_scala

import advent_of_scala.base.Solver
import advent_of_scala.utils.IO.{consolePrompt, printSolution, readLines, validateInput}
import advent_of_scala.utils.{SolutionBoilerplate, SolutionMeta}

val lastYear = 2023

def getSolverForYear(year: Int, input: List[String]): Solver =
    year match
        case 2015 => year_2015.Solver2015(input)
        case 2016 => year_2016.Solver2016(input)
        case 2017 => year_2017.Solver2017(input)
        case 2018 => year_2018.Solver2018(input)
        case 2019 => year_2019.Solver2019(input)
        case 2020 => year_2020.Solver2020(input)
        case 2021 => year_2021.Solver2021(input)
        case 2022 => year_2022.Solver2022(input)
        case 2023 => year_2023.Solver2023(input)
end getSolverForYear

def runSolver(year: Int, day: Int): Unit =
    if validateInput(year, day) then
        readLines(year, day) match
            case Some(raw_input) =>
                val solution = getSolverForYear(year, raw_input).solveFor(day)
                printSolution(solution)
            case None =>
                println(s"Puzzle $year/$day is not yet solved")
                sys.exit(1)
    else
        println(s"Invalid parameters for year and day: ($year, $day)")
    end if
end runSolver

def generateSolutionStub(year: Int, day: Int): Unit =
    if validateInput(year, day) then
        SolutionBoilerplate(year, day, true).create() match
            case Some(status) => println(status)
            case None         => println(s"Solution code for $year/$day already exists")
    else
        println(s"Invalid parameters for year and day: ($year, $day)")
    end if
end generateSolutionStub

@main def main(args: String*): Unit =
    args match
        case Nil =>
            val (year, day) = consolePrompt
            runSolver(year, day)
        case "readme" :: Nil =>
            SolutionMeta.writeReadme()
            for
                year <- 2015 to lastYear
            do
                println(SolutionMeta.writeReadMeForYear(year))
        case ("gen" | "g" | "generate") :: year :: day :: Nil =>
            generateSolutionStub(year.toInt, day.toInt)
        case year :: day :: Nil =>
            runSolver(year.toInt, day.toInt)
end main
