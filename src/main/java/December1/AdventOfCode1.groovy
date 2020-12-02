package December1

def startTime = System.currentTimeMillis()

    /*
     fill up ArrayList with values.
     */
def list = new ArrayList<Integer>()
def content = new File("AdventOfCode-1").newInputStream()
content.eachLine {line ->
    list.add(line.toInteger())
}
content.close()

    /*
    Part 1:
    for each index in list, see if valid value to sum index + valid value = 2020 exists.
    if exists, print the product of index + valid value
    */
for (index in list) {
    if (list.contains(2020 - index)) {
        println "Part 1: " + (index * (2020 - index))
        break
    }
}

    /*
    Part 2:
        nested for-loops. Not good.
    */
def part2
for (value1 in list) {
    for (value2 in list){
        def value3 = 2020 - (value1 + value2)
        if(list.contains(value3)){
            part2 = (value1 * value2 * value3)
            break
        }
    }
}
println "Part 2: " + part2

println "total time " + (System.currentTimeMillis() - startTime)