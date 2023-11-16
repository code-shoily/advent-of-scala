## Advent of Scala is Advent of Code through Scala 3

This repository contains my attempts to learn Scala 3 by solving [Advent of Code](http://adventofcode.com) problems (2015 - 2022).

### Usage

#### Running a solution

To run a particular days result, type `sbt "run <year> <day>"` or if you are on the sbt console, `sbt run <year> <day>`. Please note that `year` is 4-digits (i.e. `2015`) while day is without any leading `0` (i.e. `1`, `24`).

#### Testing

As with all sbt projects, `sbt test` or `test` (in console) runs all the tests.

#### Creating boilerplate

To quickly create boilerplate(s) for solution source, test and input files, type `sbt "run gen <year> <day>"` or (in sbt console) `sbt run gen <year> <day>`. Same format for `year` and `day` applies. Instead of `gen`, we could also use `g` or `generator`. 

This will not overwrite anything and will only generate if the the day was not already solved (i.e. none of source, input or test files exist).

### Updating solution status

In order to update the `year/README.md` with updated information, run `sbt "run readme"` to regenerate all README files reflecting the solution status.

### Progress:

#### :trophy: %s/400

%s
