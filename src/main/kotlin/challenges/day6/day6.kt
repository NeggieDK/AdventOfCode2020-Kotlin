package challenges.day6

import getResourcePath
import java.io.File
import kotlin.system.measureTimeMillis

fun execute(){
    val t = measureTimeMillis {
        val lines = File(getResourcePath("day6.txt")).readLines()
        val groups = parse(lines)
        println("Part1: ${part1(groups)}")
        println("Part2: ${part2(groups)}")
    }
    println(t)
}

fun parse(lines: List<String>): List<List<HashSet<String>>>{
    val groups = mutableListOf<List<HashSet<String>>>()
    var tempGroup = mutableListOf<HashSet<String>>()

    for(line in lines.withIndex()){
        if(line.value.isBlank()){
            groups.add(tempGroup)
            tempGroup = mutableListOf()
            continue
        }

        tempGroup.add(line.value.map { it.toString() }.toHashSet())

        if(line.index == lines.size - 1){
            groups.add(tempGroup)
        }
    }
    return groups
}

fun part1(groups: List<List<HashSet<String>>>): Int{
    val result = mutableListOf<HashSet<String>>()

    for(group in groups){
        val uniqueAnswers = hashSetOf<String>()
        for(person in group){
            for(answer in person){
                uniqueAnswers.add(answer)
            }
        }
        result.add(uniqueAnswers)
    }

    return result.sumBy { it.size }
}

fun part2(groups: List<List<HashSet<String>>>): Int{
    val result = mutableListOf<Int>()
    for(group in groups) {
        var common = group.first()
        for (answers in group.drop(1)) {
            common = answers.filter { common.contains(it) }.toHashSet()

            if(common.isEmpty()) break
        }
        result.add(common.size)
    }

    return result.sum()
}
