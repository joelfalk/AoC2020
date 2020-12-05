def content = new File('AdventOfCode4').newInputStream()

Map<Integer, List<String>> map = new HashMap<>()
def key = 0
content.splitEachLine('\\s',
        {
            line ->
                if (line.get(0) != "") {
                    if (map.get(key) == null) {
                        map.put(key, line)
                    } else {
                        line += map.get(key)
                        map.put(key, line)
                    }
                } else {
                    key++
                }
        }
)

def count = 0
count = map.count { x -> x.getValue().size() == 8 }
map.each { x ->
    if (x.getValue().size() == 7) {
        def bool = true
        x.getValue().each { str ->
            if (str.contains("cid")) {
                bool = false
            } else {
            }
        }
        if (bool == true) {
            count++
        }
    }
}
println(count)
/*
def result = map.entrySet().stream()
        .filter({ fieldsCondition -> fieldsCondition.getValue().size() >= 7 })
        .filter({ fields -> fields.getValue().each { cIdCondition -> cIdCondition.substring(0, 2) == "cid" } })
        .forEach({ s -> count++ })


map.println()
return "Part 1: " + result
*/
