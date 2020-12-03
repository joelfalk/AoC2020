def content = new File("AdventOfCode2").newInputStream()

/*
    PART 1:
    manipulate input data by splitting
    each row on regex "-", ": " and " ", which
    will result in a list of:  [ MIN, MAX, CHAR, PASSWORD ]

    for each row, count if PASSWORD contains CHAR
    at least MIN times and at most MAX times.
 */

def result1 = 0

content.splitEachLine('-|:+\\s|\\s',
        { row ->
            def min = row[0].toInteger()
            def max = row[1].toInteger()
            def character = row[2]
            def password = row[3]
            if (password.count(character) >= min && password.count(character) <= max) {
                result1++
            }
        }
)

println('Part 1: ' + result1)

/*
    PART 2:
    split up the input data as part 1
    for each row compare charAtPos1 and charAtPos2 with correctChar
    if one is correct, no matter which, increase result2 by 1.
 */

def content2 = new File("AdventOfCode2").newInputStream()

def result2 = 0

content2.splitEachLine('-|:+\\s|\\s',
        { row ->
            def correctChar = row[2]
            def charAtPos1 = row[3].charAt(row[0].toInteger() - 1)
            def charAtPos2 = row[3].charAt(row[1].toInteger() - 1)
            if (charAtPos1 == correctChar ^ charAtPos2 == correctChar) {
                result2++
            }

        }
)

println("Part 2: " + result2)
