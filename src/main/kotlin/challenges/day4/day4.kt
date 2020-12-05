package challenges.day4

import getResourcePath
import java.io.File

fun execute(){
    val lines = File(getResourcePath("day4.txt")).readLines()
    val passportsPart1 = parseInput(lines).map { Passport.fromKVPair(it, ::validatePart1) }
    val passportsPart2 = parseInput(lines).map { Passport.fromKVPair(it, ::validatePart2) }
    val validPassports = passportsPart1.map { it.validate() }.count { it }
    println("Part1: $validPassports")

    val validPassports2 = passportsPart2.map { it.validate() }.count { it }
    println("Part2: $validPassports2")
}

fun parseInput(input: List<String>): List<String>{
    val result = mutableListOf<String>()
    var tempStr = ""
    for(line in input.withIndex()){
        tempStr = tempStr + " " + line.value

        if(line.value.isBlank() || line.index == input.size-1){
            result.add(tempStr)
            tempStr = ""
        }
    }

    return result
}
