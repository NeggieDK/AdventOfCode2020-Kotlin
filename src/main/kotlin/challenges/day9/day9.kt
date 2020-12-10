package challenges.day9

import getResourcePath
import java.io.File

fun execute(){
    val numbers = File(getResourcePath("day9.txt")).readLines().map { it.toLong() }
    val part1 = part1(25, numbers)
    println("Part1: ${part1}")
    val part2 = part2(numbers, part1)
    println("Part2: ${part2.min()?.plus(part2.max()!!)}")

}

fun part1(preambleLength: Int, numbers: List<Long>): Long{
    for(i in preambleLength..numbers.size){
        val subList = numbers.subList(i-preambleLength, i).toHashSet()
        val numberToFind = numbers[i]

        var found = false
        for(subItem in subList){
            if(subItem >= numberToFind) continue

            if(subList.contains(numberToFind-subItem)){
                found = true
                break
            }
        }

        if(!found){
            return numberToFind
        }
    }
    return 0L
}

fun part2(numbers: List<Long>, invalidNumber: Long): List<Long>{
    for(i in 0 until numbers.size){

        if(numbers[i] + numbers[i+1] > invalidNumber) continue

        val numbersUsed = mutableListOf(numbers[i])
        var sum = numbers[i]
        var index = i + 1
        while(sum < invalidNumber){
            sum += numbers[index]
            numbersUsed.add(numbers[index])
            if(sum == invalidNumber){
                return numbersUsed
            }
            index++
        }
    }
    return mutableListOf()
}