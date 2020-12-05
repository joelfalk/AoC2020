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
