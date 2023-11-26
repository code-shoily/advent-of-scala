package advent_of_scala.base

type Solution = (Int, Int) | (Long, Long) | (String, String) | (Int, String) | (String, Int)

def didNotSolveError(year: Int, day: Int): Nothing = sys.error(s"Did not solve $year/$day yet")
def impossibleStateError: Nothing = sys.error("Impossible state reached.")

trait Solver:
    def solveFor(day: Int): Solution
