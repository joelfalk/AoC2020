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
                    map.get(key).sort()
                    key++
                }
        }
)

/*
    Part 1:
    Use Java-streams and helpMethod hasValidFields
    to confirm if passport contains All of the
    required fields:
     ["byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"]
 */
long part1 = map.values().stream()
        .filter({ passports -> hasValidPassportFields(passports) })
        .count()

private static boolean hasValidPassportFields(List<String> list) {
    List reqFields = ["byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"]

    List<String> fieldIdentifier = list.stream()
            .map({ field -> field.substring(0, 3) })
            .collect(Collectors.toList())

    return fieldIdentifier.containsAll(reqFields)
}

/*
    Part 2:
    filter based on fields, then sequencial for each
    value based condition for each field.

 */
long part2 = map.values().stream()
        .filter({ passport -> hasValidPassportFields(passport) })
        .filter({ passport -> validPassportFieldsValues(passport) })
        .count()

private static boolean validPassportFieldsValues(List<String> list) {
    list.sort()

    List<String> splitList = list.stream()
            .filter({ s -> s.substring(0, 3) != "cid" })
            .map({ s -> s.substring(4) })
            .collect(Collectors.toList())

    return validBirthYear(splitList[0]) &&
            validEyeColor(splitList[1]) &&
            validExpirationYear(splitList[2]) &&
            validHairColor(splitList[3]) &&
            validHeight(splitList[4]) &&
            validIssueYear(splitList[5]) &&
            validPassportId(splitList[6])


}

private static boolean validBirthYear(String birthYear) {
    def t = birthYear.size() == 4 && birthYear.toInteger() >= 1920 && birthYear.toInteger() <= 2002
    return birthYear.size() == 4 && birthYear.toInteger() >= 1920 && birthYear.toInteger() <= 2002
}

private static boolean validEyeColor(String eyeColor) {
    def validEyeColors = ["amb", "blu", "brn", "gry", "grn", "hzl", "oth"]
    def e = validEyeColors.contains(eyeColor) && eyeColor.size() == 3
    return validEyeColors.contains(eyeColor) && eyeColor.size() == 3
}

private static boolean validExpirationYear(String experationYear) {
    def c = experationYear.size() == 4 && experationYear.toInteger() >= 2020 && experationYear.toInteger() <= 2030
    return experationYear.size() == 4 && experationYear.toInteger() >= 2020 && experationYear.toInteger() <= 2030
}

private static boolean validHairColor(String hairColor) {
    def g = hairColor.getAt(0) == "#" && hairColor.substring(1).matches("[A-Fa-f0-9]{6}")
    return hairColor.getAt(0) == "#" && hairColor.substring(1).matches("[A-Fa-f0-9]{6}")
}

private static boolean validHeight(String height) {
    if (height.substring(height.size() - 2) == "cm") {
        def s = 150 <= height.substring(0, height.size() - 2).toInteger() && height.substring(0, height.size() - 2).toInteger() <= 193
        return 150 <= height.substring(0, height.size() - 2).toInteger() && height.substring(0, height.size() - 2).toInteger() <= 193
    } else if (height.substring(height.size() - 2) == "in") {
        def a = 59 <= height.substring(0, height.size() - 2).toInteger() && height.substring(0, height.size() - 2).toInteger() <= 76
        return 59 <= height.substring(0, height.size() - 2).toInteger() && height.substring(0, height.size() - 2).toInteger() <= 76
    } else {
        return false
    }
}

private static boolean validIssueYear(String issueYear) {
    def sx = issueYear.size() == 4 && issueYear.toInteger() >= 2010 && issueYear.toInteger() <= 2020
    return issueYear.size() == 4 && issueYear.toInteger() >= 2010 && issueYear.toInteger() <= 2020
}

private static boolean validPassportId(String passportId) {
    def er = passportId.matches("[0-9]{9}")
    return passportId.matches("[0-9]{9}")
}


println("Part 1: " + part1)
println("Part 2: " + part2)
