/*
    fill upp array with list of strings from input data
    which was split row by row by "-", ": " and " ".
    This in each row as an list of:  [ MIN, MAX, CHAR, PASSWORD ]

    for each row, count if PASSWORD contains CHAR
    at least MIN times and at most MAX times.
 */
def result = 0

def content = new File("AdventOfCode2").newInputStream()

content.splitEachLine('-|:+\\s|\\s',
        { row ->
            if (row[3].count(row[2]) >= row[0].toInteger() && row[3].count(row[2]) <= row[1].toInteger()) {
                result++
            }
        }
)

print(result)


