package challenges.day4

fun validatePart2(passport: Passport): Boolean {
    if (passport.BirthYear.isNullOrEmpty() || !passport.BirthYear.canBeParsedToInt() || passport.BirthYear.toInt() < 1920 || passport.BirthYear.toInt() > 2002) {
        return false
    }

    if (passport.IssueYear.isNullOrEmpty() || !passport.IssueYear.canBeParsedToInt() || passport.IssueYear.toInt() < 2010 || passport.IssueYear.toInt() > 2020) {
        return false
    }

    if (passport.ExpiryYear.isNullOrEmpty() || !passport.ExpiryYear.canBeParsedToInt() || passport.ExpiryYear.toInt() < 2020 || passport.ExpiryYear.toInt() > 2030) {
        return false
    }

    if (passport.height.isNullOrEmpty()) return false
    if (passport.height.contains("cm")) {
        val height = passport.height.substring(0, 3)
        if (!height.canBeParsedToInt()) return false

        if (height.toInt() < 150 || height.toInt() > 193) return false
    } else if (passport.height.contains("in")) {
        val height = passport.height.substring(0, 2)
        if (!height.canBeParsedToInt()) return false

        if (height.toInt() < 59 || height.toInt() > 76) return false
    } else {
        return false
    }

    if (passport.HairColor.isNullOrEmpty() || passport.HairColor.length != 7 || passport.HairColor[0].toString() != "#") return false
    for (cItem in passport.HairColor.substring(1, passport.HairColor.length)) {
        if (!cItem.isLetterOrDigit()) return false
        val allowedChars = hashSetOf("a", "b", "c", "d", "e", "f")
        if (cItem.isDigit()) {
            if (Character.getNumericValue(cItem) < 0 || Character.getNumericValue(cItem) > 9) return false
        }
        if (cItem.isLetter()) {
            if (!allowedChars.contains(cItem.toString())) return false
        }
    }

    val validColors = hashSetOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    if (passport.EyeColor.isNullOrEmpty()) return false
    if (!validColors.contains(passport.EyeColor.trim())) return false

    if (passport.PID.isNullOrEmpty() || !passport.PID.canBeParsedToInt() || passport.PID.length != 9) return false

    return true
}


fun String.canBeParsedToInt(): Boolean{
    return try{
        this.toInt()
        true
    }
    catch(e: NumberFormatException){
        false
    }
}