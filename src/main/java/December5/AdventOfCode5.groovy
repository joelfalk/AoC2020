import java.util.stream.Collectors
import java.util.stream.IntStream

def content = new File('AdventOfCode5').newInputStream()
def map = new TreeMap<String, Map<List<Integer>, List<Integer>>>()
content.eachLine { str -> map.put(str, seatingMap()) }

def answer = map.entrySet().stream()
        .map({ a -> a.setValue(calcSeat(a.key, a.value)) })
        .collect()

println(answer)

Map<List<Integer>, List<Integer>> calcSeat(String codedSeating, Map<List<Integer>, List<Integer>> mapCalc) {
    def oldKey = mapCalc.entrySet().getAt(0).key
    def oldValue = mapCalc.entrySet().getAt(0).value
    if (codedSeating.size() > 3) {
        if (codedSeating[0] == 'F') {
            def newKey = mapCalc.entrySet().getAt(0).key.subList(0, mapCalc.entrySet().getAt(0).key.size() / 2 as int)
            mapCalc.remove(oldKey)
            mapCalc.put(newKey, oldValue)
            return calcSeat(codedSeating.substring(1), mapCalc)
        } else {
            def newKey = mapCalc.entrySet().getAt(0).key.subList(mapCalc.entrySet().getAt(0).key.size() / 2 as int, mapCalc.entrySet().getAt(0).key.size() as int)
            mapCalc.remove(oldKey)
            mapCalc.put(newKey, oldValue)
            return calcSeat(codedSeating.substring(1), mapCalc)
        }
    } else {
        if (codedSeating.size() == 1) {
            if (codedSeating == "L") {
                def newValue = mapCalc.entrySet().getAt(0).value.subList(0, mapCalc.entrySet().getAt(0).value.size() / 2 as int)
                mapCalc.replace(oldKey, newValue)
                return mapCalc
            } else {
                def newValue = mapCalc.entrySet().getAt(0).value.subList(mapCalc.entrySet().getAt(0).value.size() / 2 as int, mapCalc.entrySet().getAt(0).value.size() as int)
                mapCalc.replace(oldKey, newValue)

                return mapCalc
            }
        }
        if (codedSeating[0] == 'L') {
            def newValue = mapCalc.entrySet().getAt(0).value.subList(0, mapCalc.entrySet().getAt(0).value.size() / 2 as int)
            mapCalc.replace(oldKey, newValue)
            return calcSeat(codedSeating.substring(1), mapCalc)
        } else {
            def newValue = mapCalc.entrySet().getAt(0).value.subList(mapCalc.entrySet().getAt(0).value.size() / 2 as int, mapCalc.entrySet().getAt(0).value.size() as int)
            mapCalc.replace(oldKey, newValue)
            return calcSeat(codedSeating.substring(1), mapCalc)
        }
    }
}

private Map<List<Integer>, List<Integer>> seatingMap() {
    Map<List<Integer>, List<Integer>> rowMap = new HashMap<>(127)
    List<Integer> columnRow = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7)
    List<Integer> rows = new ArrayList<Integer>()
    IntStream.range(0, 128).forEach({ i -> rows.add(i) })
    IntStream.range(0, 128).forEach({ i -> rowMap.put(rows, columnRow) })
    return rowMap
}

//guessed 968