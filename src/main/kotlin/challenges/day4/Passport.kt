package challenges.day4

class Passport(val BirthYear: String?,
               val IssueYear: String?,
               val ExpiryYear: String?,
               val height: String?,
               val HairColor: String?,
               val EyeColor: String?,
               val PID: String?,
               val CountryId: String?,
               val validatorFunc: (Passport)-> Boolean){

    companion object{
        fun fromKVPair(input: String, validateFunc: (Passport)-> Boolean): Passport{
            val pairs = hashMapOf<String, String>()
            input.split(' ').filter { !it.isBlank() }.map {it.split(':')  }.forEach {
                pairs[it[0]] = it[1]
            }

            return Passport(
                BirthYear = pairs["byr"],
                IssueYear = pairs["iyr"],
                ExpiryYear = pairs["eyr"],
                height = pairs["hgt"],
                HairColor = pairs["hcl"],
                EyeColor = pairs["ecl"],
                PID = pairs["pid"],
                CountryId = pairs["cid"],
                validatorFunc = validateFunc
            )
        }
    }

    fun validate(): Boolean{
        return validatorFunc.invoke(this)
    }
}