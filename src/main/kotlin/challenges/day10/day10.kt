package challenges.day10

import getResourcePath
import java.io.File
import kotlin.system.measureTimeMillis

fun execute(){
    val numbers = File(getResourcePath("day10.txt")).readLines().map { it.toLong() }.toHashSet()
    println("Part1: ${part1(numbers, 0, 0, 0)}")

//    val bf = measureTimeMillis {
//        println("Part2: ${part2BruteForce(numbers, 0)}")
//    }
//    println(bf)

    val memo = measureTimeMillis {
        println("Part2: ${part2Memo(numbers, 0, hashMapOf())}")
    }
    println(memo)
}

fun part1(numbers: HashSet<Long>, amount1: Long, amount3: Long, currentAdapter: Long): Long{

    if(currentAdapter == numbers.max()){
        return amount1 * (amount3 + 1)
    }

    return when {
        numbers.contains(currentAdapter + 1) -> {
            part1(numbers, amount1 + 1, amount3, currentAdapter+ 1)
        }
        numbers.contains(currentAdapter + 2) -> {
            part1(numbers, amount1, amount3, currentAdapter+ 2)
        }
        numbers.contains(currentAdapter + 3) -> {
            part1(numbers, amount1, amount3 + 1, currentAdapter+ 3)
        }
        else -> 0
    }
}

fun part2Memo(numbers: HashSet<Long>, currentAdapter: Long, memoization: HashMap<Long, Long>): Long{
    if(currentAdapter == numbers.max()) {
        return 1
    }

    var plus1 = 0L
    if(numbers.contains(currentAdapter + 1)){
        if(memoization.containsKey(currentAdapter + 1)){
            plus1 = memoization[currentAdapter + 1] ?: 0
        } else{
            plus1 = part2Memo(numbers, currentAdapter + 1, memoization)
            memoization[currentAdapter + 1] = plus1
        }
    }

    var plus2 = 0L
    if(numbers.contains(currentAdapter + 2)){
        if(memoization.containsKey(currentAdapter + 2)){
            plus2 = memoization[currentAdapter + 2] ?: 0
        } else{
            plus2 = part2Memo(numbers, currentAdapter + 2, memoization)
            memoization[currentAdapter + 2] = plus2
        }
    }

    var plus3 = 0L
    if(numbers.contains(currentAdapter + 3)){
        if(memoization.containsKey(currentAdapter + 3)){
            plus3 = memoization[currentAdapter + 3] ?: 0
        } else{
            plus3 = part2Memo(numbers, currentAdapter + 3, memoization)
            memoization[currentAdapter + 3] = plus3
        }
    }
    return plus1 + plus2 + plus3
}

fun part2BruteForce(numbers: HashSet<Long>, currentAdapter: Long): Long{
    if(currentAdapter == numbers.max()) {
        return 1
    }

    var plus1 = 0L
    if(numbers.contains(currentAdapter + 1)){
        plus1 =  part2BruteForce(numbers, currentAdapter + 1)
    }

    var plus2 = 0L
    if(numbers.contains(currentAdapter + 2)){
        plus2 =  part2BruteForce(numbers, currentAdapter + 2)
    }

    var plus3 = 0L
    if(numbers.contains(currentAdapter + 3)){
        plus3 =  part2BruteForce(numbers, currentAdapter + 3)
    }
    return plus1 + plus2 + plus3
}