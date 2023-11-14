## Advent of Scala is Advent of Code through Scala 3

This repository contains my attempts to learn Scala 3 by solving [Advent of Code](http://adventofcode.com) problems (2015 - 2022).

### Usage

#### Running a solution

To run a particular days result, type `sbt "run <year> <day>"` or if you are on the sbt console, `sbt run <year> <day>`. Please note that `year` is 4-digits (i.e. `2015`) while day is without any leading `0` (i.e. `1`, `24`).

#### Creating boilerplate

To quickly create boilerplate(s) for solution source, test and input files, type `sbt "run gen <year> <day>"` or (in sbt console) `sbt run gen <year> <day>`. Same format for `year` and `day` applies. Instead of `gen`, we could also use `g` or `generator`. 

This will not overwrite anything and will only generate if the the day was not already solved (i.e. none of source, input or test files exist).

### Updating solution status (WIP)

In order to update the `year/README.md` with updated information, run `sbt "run readme"` to regenerate all README files reflecting the solution status.

The following is the `README.md` for all years:

* [2015/README.md](/src/main/scala/advent_of_scala/2015/README.md)
* [2016/README.md](/src/main/scala/advent_of_scala/2016/README.md)
* [2017/README.md](/src/main/scala/advent_of_scala/2017/README.md)
* [2018/README.md](/src/main/scala/advent_of_scala/2018/README.md)
* [2019/README.md](/src/main/scala/advent_of_scala/2019/README.md)
* [2020/README.md](/src/main/scala/advent_of_scala/2020/README.md)
* [2021/README.md](/src/main/scala/advent_of_scala/2021/README.md)
* [2022/README.md](/src/main/scala/advent_of_scala/2022/README.md)

### TODO: 
 
- Create generator to update summarized view of all years on this root `README` page
- Convert generators into proper `sbt` tasks
- Add git-hooks to regenerate README everytime code is pushed
- Create tag pages to show tag statistics and connections

### Testing

As with all sbt projects, `sbt test` or `test` (in console) runs all the tests.