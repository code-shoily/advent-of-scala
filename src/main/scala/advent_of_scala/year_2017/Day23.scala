/** 2017/23: Coprocessor Coflagration
  *
  * Link: https://adventofcode.com/2017/day/23
  *
  * Difficulty: xl
  *
  * Tags: opcode reverse-engineering
  *
  * Answers: (6241, 909)
  */
package advent_of_scala.year_2017

import advent_of_scala.base.Solution
import advent_of_scala.year_2017.Day23.*
import advent_of_scala.year_2017.Day23.State.*

class Day23(rawInput: List[String]):
    def solve: Solution =
        val input = parsedInput
        val part1 =
            Iterator
                .iterate(input)(_.step)
                .dropWhile(_.state == Running)
                .next()
                .count
        val part2 =
            // from 100_000 + 81 * 100 to 108_100 + 17_000 by 17
            (108100 to 125100 by 17) count { n => (2 to math.sqrt(n).toInt) exists { n % _ == 0 } }
        (part1, part2)
    end solve

    private def parsedInput = Cpu(rawInput)
end Day23

object Day23:
    enum State:
        case Running, Halted

    case class Cpu(
        instructions: Seq[String],
        registers: Map[String, Long] = Map(),
        ip: Int = 0,
        state: State = Running,
        count: Int = 0
    ):
        def next: Cpu = copy(ip = ip + 1)
        def read(key: String): Long = key.toLongOption.getOrElse(registers.getOrElse(key, 0L))
        def write(key: String, value: Long): Cpu =
            next.copy(registers = registers.updated(key, value))
        def step: Cpu =
            if instructions.indices.contains(ip) then
                val Array(op, dest, src) = instructions(ip).split(" ")
                op match
                    case "set" => write(dest, read(src))
                    case "sub" => write(dest, read(dest) - read(src))
                    case "mul" => write(dest, read(dest) * read(src)).copy(count = count + 1)
                    case "jnz" => if read(dest) != 0 then copy(ip = ip + read(src).toInt) else next
                end match
            else
                copy(state = Halted)
        end step
    end Cpu
end Day23

/*--------- Block to test this file on IDEs, comment this line with `//` to enable.
@main def run_2017_23(): Unit =
    import advent_of_scala.base.impossibleStateError
    import advent_of_scala.utils.IO.{readLines, printSolution}
    readLines(2017, 23) match
        case Some(raw_input) =>
            printSolution(Day23(raw_input).solve)
        case _ => impossibleStateError
end run_2017_23
// */
