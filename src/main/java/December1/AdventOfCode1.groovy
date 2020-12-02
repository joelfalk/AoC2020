package December1

    /*
     fill up HashMap with values.
     */
def map = new HashMap<String, Integer>()
def content = new File("AdventOfCode-1").newInputStream()
content.eachLine {line ->
    map.put(line, line.toInteger())
}
content.close()

    /*
    for each entry in map, see if valid value to sum entry + valid value = 2020 exists.
    if exists, print the product of entry + valid value
    */
for (index in map) {
    if (map.containsValue(2020 - index.value)) {
        println index.value*(2020-index.value)
        break
    }
}