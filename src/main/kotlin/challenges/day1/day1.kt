package challenges.day1

import getResourcePath
import java.io.File
import kotlin.system.measureTimeMillis

fun execute() {
    val part1Lines = getLines(getResourcePath("day1.txt"))
    val t1 = measureTimeMillis {
        println("Part 1: ${part1(part1Lines)}")
    }
    println("Part 1 quadratic took: $t1")
    val t2 = measureTimeMillis {
        println("Part 1: ${part1HashSet(part1Lines)}")
    }
    println("Part 1 w/ hashset took: $t2")
    val t3 = measureTimeMillis {
        println("Part 2: ${part2(part1Lines)}")
    }
    println("Part 2 n^3 took: $t3")
    val t4 = measureTimeMillis {
        println("Part 2: ${part2WithHashset(part1Lines)}")
    }
    println("Part 2 w/ hashset took: $t4")
}

fun getLines(path: String): List<Int>{
    return File(path).readLines().map { it.toInt() }
}

fun part1(lines: List<Int>): Int {
    for(number1 in lines.withIndex()){
        for(number2 in lines.withIndex()){
            if(number1.index == number2.index) continue

            if(number1.value + number2.value == 2020){
                return number1.value * number2.value
            }
        }
    }
    return 0
}

fun part1HashSet(lines: List<Int>): Int{
    val result = 2020
    val linesHash = lines.toHashSet()

    for(line in lines){
        if(linesHash.contains(result-line)){
            return line * (result-line)
        }
    }
    return 0
}

fun part2(lines: List<Int>): Int {
    for(number1 in lines.withIndex()){
        for(number2 in lines.withIndex()){
            if(number1.index == number2.index) continue
            if(number1.value + number2.value >= 2020) continue
            for(number3 in lines.withIndex()) {
                if (number1.index == number3.index || number2.index == number3.index) continue

                if (number1.value + number2.value + number3.value== 2020) {
                    return number1.value * number2.value * number3.value
                }
            }
        }
    }
    return 0
}

fun part2WithHashset(lines: List<Int>): Int {
    val result = 2020
    val linesHash = lines.toHashSet()

    for(number1 in lines.withIndex()){
        for(number2 in lines.withIndex()){
            if(number1.index == number2.index) continue
            if(number1.value + number2.value >= 2020) continue

            val remaining = result - number1.value - number2.value
            if(linesHash.contains(remaining)){
                return number1.value * number2.value * remaining
            }
        }
    }
    return 0
}