import java.util.stream.Collectors

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

private static boolean validPassportFields(List<String> list) {
    List condition = ["byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"]

    return  list.containsAll(condition)
}


long s = map.values().stream()
        .map({ fields -> fields.stream().map({ field -> field.substring(0, 3)}).collect(Collectors.toList()) })
        .filter({passports -> validPassportFields(passports)})
        .count()


println("Part 1: " + s)
