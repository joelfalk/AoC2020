/*
    PART 1:
    read input data. for each row (skip first row)
    check for # symbolizing a tree at coordinate x.
    if tree -> result ++

    PART 2:
    Do part1 but for different x and y. Refactored to
    manually set stepsX and stepsY. Use modulo to check
    if stepsX should be active.
 */

private static Integer treeCounter(int stepsX, int stepsY) {
    def content = new File('AdventOfCode3').newInputStream()
    def x = stepsX
    def result = 0

    content.eachLine { row, y ->
        if (y == 1) {
            return
        }
        if ((y + 1) % stepsY == 0) {
            if (row.charAt(x) == '#') {
                result++
            }

            if (stepsX >= (row.size() - x)) {
                x = (x - row.size()) + stepsX
            } else {
                x += stepsX
            }
        }
    }
    return result
}

static void main(String[] args) {
    println(treeCounter(1, 1))
    println(treeCounter(3, 1))
    println(treeCounter(5, 1))
    println(treeCounter(7, 1))
    println(treeCounter(1, 2))
}