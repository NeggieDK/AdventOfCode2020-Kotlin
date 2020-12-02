package challenges.day2

import getResourcePath
import java.io.File
import kotlin.system.measureTimeMillis

fun execute(){
    val t = measureTimeMillis {
        val unparsedPasswords = File(getResourcePath("day2.txt")).readLines()
        val passwords = unparsedPasswords.map { parseLine(it) }

        val validPasswordsPart1 = part1(passwords)
        println("Part1: ${validPasswordsPart1.size}")

        val validPasswordsPart2 = part2(passwords)
        println("Part1: ${validPasswordsPart2.size}")
    }
    println("Day2 took ${t}ms")
}

fun part1(passwords: List<Password>): List<String>{
    return passwords.filter { it.validatePart1() }.map { it.password }
}

fun part2(passwords: List<Password>): List<String>{
    return passwords.filter { it.validatePart2() }.map { it.password }
}

fun parseLine(input: String): Password{
    val sections = input.split(" ")
    val limits = sections[0].trim().split("-")
    val letter =  sections[1].trim().replace(":", "")
    val password = sections[2].trim()
    return Password(limits[0].toShort(), limits[1].toShort(), letter, password)
}