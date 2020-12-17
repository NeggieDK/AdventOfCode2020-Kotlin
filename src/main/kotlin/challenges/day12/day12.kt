package challenges.day12

import getResourcePath
import java.io.File

fun execute(){
    val instructions = File(getResourcePath("day12.txt")).readLines().map { Pair(it[0].toString(), it.substring(1).toInt()) }
    val ship = Ship(0,0)
    instructions.forEach {
        ship.move(it.first, it.second)
    }
    println("Part1: ${ship.getManhattanDistance()}")

    val wp = WayPoint(10, 1)
    val part2Ship = ShipWaypoints(0,0, wp)
    instructions.forEach {
        part2Ship.parseInstruction(it.first, it.second)
    }
    println("Part1: ${part2Ship.getManhattanDistance()}")
}