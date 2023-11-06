package base

type Solution = (Int, Int)

trait Solver:
  def solveFor(day: Int): Solution
