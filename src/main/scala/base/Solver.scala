package advent_of_scala.base

type Solution = (Int, Int)

def didNotSolve(year: Int, day: Int): String = s"Did not solve $year/$day yet"

trait Solver:
  def solveFor(day: Int): Solution
