/*
    PART 1:
    read input data. for each row (skip first row)
    check for # symbolizing a tree at coordinate x.
    if tree -> result ++
 */
def content = new File('AdventOfCode3').newInputStream()
def x = 0
def result = 0

content.eachLine( { row ->
    if (row.charAt(x) == '#') {
        result++
    }
    if (x >= 28) {
        x = (x - 31) + 3
    } else {
        x += 3
    }
}
)

println(result)