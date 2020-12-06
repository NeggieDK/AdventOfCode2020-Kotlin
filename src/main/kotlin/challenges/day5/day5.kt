package challenges.day5

import getResourcePath
import java.io.File

fun execute(){
    val lines = File(getResourcePath("day5.txt")).readLines()
    println("Part1: ${part1(lines)}")
    println("Part1: ${part2(lines)}")
}

fun part1(lines: List<String>): Int{
    val seatPositions =  lines.map { SeatPosition(getRow(it.substring(0, 7), Pair(0, 127)).first, getColumn(it.substring(7, it.length), Pair(0, 7)).first) }
    return seatPositions.maxBy { it.getSeatID() }?.getSeatID() ?: 0
}

fun part2(lines: List<String>): Int{
    val seatPositions =  lines.map { SeatPosition(getRow(it.substring(0, 7), Pair(0, 127)).first, getColumn(it.substring(7, it.length), Pair(0, 7)).first) }.map { it.getSeatID() }.toHashSet()

    for(possibleSeatID in 1 until ((127*8)+7)) { // very first and last seat can't be it, because a seatID -1 (for 0) or seatID + 1 (for last) do not exist
        if (seatPositions.contains(possibleSeatID)) continue

        //You only get here if the seat doesn't exist
        if (seatPositions.contains(possibleSeatID + 1) && seatPositions.contains(possibleSeatID - 1))
            return possibleSeatID
    }

    return 0
}

fun getRow(input: String, currentPair: Pair<Int, Int>): Pair<Int, Int>{
    val instruction = input.first().toString()
    if(input.length == 1) {
        return if(instruction == "F"){
            Pair(currentPair.first, currentPair.first)
        }
        else{
            Pair(currentPair.second, currentPair.second)
        }
    }

    val difference = (currentPair.second - currentPair.first)/2
    return if(instruction == "F"){
        val nPair = Pair(currentPair.first, currentPair.second-difference-1)
        getRow(input.substring(1,input.length), nPair)
    }
    else{
        val nPair = Pair(currentPair.first + difference + 1, currentPair.second)
        getRow(input.substring(1,input.length), nPair)
    }
}

fun getColumn(input: String, currentPair: Pair<Int, Int>): Pair<Int, Int>{
    val instruction = input.first().toString()
    if(input.length == 1) {
        return if(instruction == "L"){
            Pair(currentPair.first, currentPair.first)
        }
        else{
            Pair(currentPair.second, currentPair.second)
        }
    }

    val difference = (currentPair.second - currentPair.first)/2
    return if(instruction == "L"){
        val nPair = Pair(currentPair.first, currentPair.second-difference-1)
        getColumn(input.substring(1,input.length), nPair)
    }
    else{
        val nPair = Pair(currentPair.first + difference + 1, currentPair.second)
        getColumn(input.substring(1,input.length), nPair)
    }
}