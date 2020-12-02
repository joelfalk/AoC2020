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
    for each index in list, see if valid value to sum index + valid value = 2020 exists.
    if exists, print the product of index + valid value
    */
for (index in list) {
    if (list.contains(2020 - index)) {
        println index*(2020-index)
        break
    }
}

println "total time " + (System.currentTimeMillis() - startTime)