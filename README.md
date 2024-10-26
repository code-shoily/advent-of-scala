## Advent of Scala is Advent of Code through Scala 3

This repository contains my attempts to learn Scala 3 by solving [Advent of Code](http://adventofcode.com) problems (
2015 - 2022).

### Usage

#### Running a solution

To run a particular days result, type `sbt "run <year> <day>"` or if you are on the sbt console, `sbt run <year> <day>`.
Please note that `year` is 4-digits (i.e. `2015`) while day is without any leading `0` (i.e. `1`, `24`).

#### Testing

As with all sbt projects, `sbt test` or `test` (in console) runs all the tests.

#### Creating boilerplate

To quickly create boilerplate(s) for solution source, test and input files, type `sbt "run gen <year> <day>"` or (in sbt
console) `sbt run gen <year> <day>`. Same format for `year` and `day` applies. Instead of `gen`, we could also use `g`
or `generator`.

This will not overwrite anything and will only generate if the the day was not already solved (i.e. none of source,
input or test files exist).

### Updating solution status

In order to update the `year/README.md` with updated information per year, and `README.md` with summarized solution
status of all years- run `sbt "run readme"` to regenerate all README files reflecting the solution status.

Please note, there are two parts of the `README.md` content - static and dynamic. The instructions to run and test the
system (including this text) is the static part, and the table (that you see below) is the dynamic part, which get
updated every time a solution is attempted. If we want to change the static content, then instead of updating
`README.md` (as it will be overriden any way) we should edit the `/src/main/resources/templates/README_TEMPLATE.md` file
with the information.

### Progress:

#### :trophy: 96/450

|:calendar:|[2015](/src/main/scala/advent_of_scala/year_2015)|[2016](/src/main/scala/advent_of_scala/year_2016)|[2017](/src/main/scala/advent_of_scala/year_2017)|[2018](/src/main/scala/advent_of_scala/year_2018)|[2019](/src/main/scala/advent_of_scala/year_2019)|[2020](/src/main/scala/advent_of_scala/year_2020)|[2021](/src/main/scala/advent_of_scala/year_2021)|[2022](/src/main/scala/advent_of_scala/year_2022)|[2023](/src/main/scala/advent_of_scala/year_2023)|
|:-:| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
|1|:1st_place_medal:|:1st_place_medal:|:1st_place_medal:|:1st_place_medal:|:1st_place_medal:|:1st_place_medal:|:1st_place_medal:|:1st_place_medal:|:1st_place_medal:|
|2|:1st_place_medal:|:1st_place_medal:|:1st_place_medal:|:1st_place_medal:||:1st_place_medal:|:1st_place_medal:|:1st_place_medal:||
|3|:1st_place_medal:|:1st_place_medal:||||:1st_place_medal:|:1st_place_medal:|:1st_place_medal:||
|4|:1st_place_medal:|||||:1st_place_medal:|:1st_place_medal:|:1st_place_medal:||
|5|:1st_place_medal:||||||:1st_place_medal:|:1st_place_medal:||
|6|||||||:1st_place_medal:|:1st_place_medal:||
|7||||||||||
|8|||||||:1st_place_medal:|||
|9||||||:1st_place_medal:||||
|10||||||||||
|11||:1st_place_medal:||||||||
|12|||||||:1st_place_medal:|||
|13|||||||:1st_place_medal:|||
|14||:1st_place_medal:||||||||
|15||:1st_place_medal:||||||||
|16||||||||||
|17||:1st_place_medal:||||||||
|18||:1st_place_medal:||||||||
|19||:1st_place_medal:|:1st_place_medal:|||||||
|20|||:1st_place_medal:|||||||
|21|||:1st_place_medal:|||||||
|22||:1st_place_medal:|:1st_place_medal:|||||||
|23|||:1st_place_medal:|||||||
|24||:1st_place_medal:|:1st_place_medal:|||||||
|25||||||||||