import java.util.stream.Collectors

def content = new File('AdventOfCode4').newInputStream()

/*
    Added input data to a map. Add each line as value to same
    key until blank line, then add row to key++.
    Each key will represent a passport.
 */
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

/*
    use Java-streams to only use field identifier,
    then filter out the invlaidPassports by
    comfirming passport contains All of the
    required fields: ["byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"]
 */
long s = map.values().stream()
        .map({ fields ->
            fields.stream()
                    .map({ field -> field.substring(0, 3) })
                    .collect(Collectors.toList())
                    }
            )
        .filter({ passports -> validPassportFields(passports) })
        .count()


private static boolean validPassportFields(List<String> list) {
    List reqFields = ["byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"]
    return list.containsAll(reqFields)
}

println("Part 1: " + s)
