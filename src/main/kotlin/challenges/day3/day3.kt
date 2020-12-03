package challenges.day3

import getResourcePath
import java.io.File

fun execute(){
    val unparsedGrid = File(getResourcePath("day3.txt")).readLines()
    val grid = Grid()
    grid.parseFromString(unparsedGrid)

    val numberOfTrees = part1(1, 3, grid)
    println("Part1: $numberOfTrees")

    val r1d1 = part1(1, 1, grid)
    val r3d1 = part1(1, 3, grid)
    val r5d1 = part1(1, 5, grid)
    val r7d1 = part1(1, 7, grid)
    val r1d2 = part1(2, 1, grid)
    println("Part2: ${r1d1*r3d1*r5d1*r7d1*r1d2}")
}

fun part1(down: Int, right: Int, grid: Grid): Long{
    var currX = 0
    var currY = 0
    var trees = 0L

    while(currY < grid.height){
        val item = grid.getLocation(currX, currY)
        if(item == "#") trees++

        currX += right
        if(currX >= grid.length) currX -= grid.length
        currY += down
    }

    return trees
}