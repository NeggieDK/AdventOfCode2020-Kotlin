package challenges.day7

import getResourcePath
import java.io.File
import kotlin.system.measureTimeMillis

fun execute(){
    val rules = File(getResourcePath("day7.txt")).readLines().map { LuggageRuleSet.fromString(it) }
    val t = measureTimeMillis {
        println("Part1: ${part1(rules, listOf("shiny gold"), mutableListOf()).size}")
        println("Part2: ${part2(rules, listOf(Pair("shiny gold", 1)), 0)}")
    }
    println(t)
}

fun part1(rules: List<LuggageRuleSet>, bagsToFind: List<String>, possibleBags: MutableList<String>): List<String>{
    if(bagsToFind.isEmpty()){
        return possibleBags
    }

    val tempBagsToFind = mutableListOf<String>()
    for(bag in bagsToFind){
        val bags = rules.filter { it.containsBag(bag) }.map { it.name }
        possibleBags.addAll(bags)
        tempBagsToFind.addAll(bags)
    }

    if(tempBagsToFind.distinct().isEmpty()){
        return possibleBags
    }

    return part1(rules, tempBagsToFind.distinct(), possibleBags.distinct() as MutableList<String>)
}

fun part2(rules: List<LuggageRuleSet>, bagsToSearch: List<Pair<String, Int>>, amount: Int): Int{
    if(bagsToSearch.isEmpty()){
        return amount
    }

    var tempAmount = amount
    val tempBagsToSearch = mutableListOf<Pair<String, Int>>()
    for(bag in bagsToSearch){
        val bags = rules.filter { it.name == bag.first }.map { it.contains }
        val t = bags.flatten()
        tempAmount += t.sumBy { it.quantity } * bag.second
        tempBagsToSearch.addAll(t.map { Pair(it.name, it.quantity*bag.second) })
    }

    return part2(rules, tempBagsToSearch.distinct(), tempAmount)
}