package challenges.day11

import getResourcePath
import java.io.File

fun execute(){
    val lines = File(getResourcePath("day11.txt")).readLines()
    val seatingGrid = SeatingGrid.fromInput(lines)
    val seatingGrid2 = SeatingGrid.fromInput(lines)
    println(part1(seatingGrid))
    println(part2(seatingGrid2))
}

fun part1(seatingGrid: SeatingGrid): Int{
    while(seatingGrid.stepPart1()){}
    return seatingGrid.getAmountOccupied()
}

fun part2(seatingGrid: SeatingGrid): Int{
    while(seatingGrid.stepPart2()){}
    return seatingGrid.getAmountOccupied()
}