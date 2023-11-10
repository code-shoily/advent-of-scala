import advent_of_scala.{
  year_2015,
  year_2016,
  year_2017,
  year_2018,
  year_2019,
  year_2020,
  year_2021,
  year_2022
}
import advent_of_scala.base.Solver
import advent_of_scala.utils.io.{consolePrompt, printSolution, readLines, validateInput}

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

@main def main: Unit =
  val (year, day) = consolePrompt

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
    sys.exit(1)
