package challenges.day1

import java.io.File
import kotlin.system.measureTimeMillis

fun execute(){
    val t = measureTimeMillis {
        val part1Lines = getLines(getResourcePath("day1.txt"))
        val t1 = measureTimeMillis {
            println("Part 1: ${part1(part1Lines)}")
        }
        println(t1)
        val t2 = measureTimeMillis {
            println("Part 1 with hashset: ${part1HashSet(part1Lines)}")
        }
        println(t2)
        println("Part 2: ${part2(part1Lines)}")
    }
    println("Day 1 took ${t}ms to execute.")
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