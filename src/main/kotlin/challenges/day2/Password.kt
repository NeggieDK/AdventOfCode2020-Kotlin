package challenges.day2

class Password(private val minimum: Short, private val maximum: Short, private val letter: String, val password: String){

    fun validatePart1(): Boolean{
        var count = 0
        for(i in password){
            if(i.toString() == letter){
                count++
            }

            if(count > maximum){
                return false
            }
        }

        return count in minimum..maximum
    }

    fun validatePart2(): Boolean{
        val letter1 = password[minimum.toInt() - 1].toString()
        val letter2 = password[maximum.toInt() - 1].toString()
        return (letter1 == letter).xor(letter2 == letter)
    }
}