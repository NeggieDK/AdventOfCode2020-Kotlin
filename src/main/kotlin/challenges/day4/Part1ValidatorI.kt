package challenges.day4

fun validatePart1(passport: Passport): Boolean {
    return passport.BirthYear != null
            && passport.IssueYear != null
            && passport.ExpiryYear != null
            && passport.height != null
            && passport.HairColor != null
            && passport.EyeColor != null
            && passport.PID != null
}