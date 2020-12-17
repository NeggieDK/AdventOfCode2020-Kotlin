package challenges.day12

import kotlin.math.abs


class ShipWaypoints(var east: Int, var north: Int, val wayPoint: WayPoint) {

    fun parseInstruction(instruction: String, value: Int){
        if(instruction == "F"){
            repeat(value){
                move()
            }
        }
        else{
            wayPoint.parseInstruction(instruction, value)
        }
    }

    private fun move(){
        north += wayPoint.north
        east += wayPoint.east
    }

    fun getManhattanDistance(): Int{
        return abs(east) + abs(north)
    }
}

class WayPoint(var east: Int, var north: Int){

    fun parseInstruction(instruction: String, value: Int){
        if(instruction == "R" || instruction == "L"){
            rotate(instruction, value)
        }
        else{
            move(instruction, value)
        }
    }

    private fun move(instruction: String, value: Int) {
        when (instruction) {
            "N" -> {
                north += value
            }
            "S" -> {
                north -= value
            }
            "E" -> {
                east += value
            }
            "W" -> {
                east -= value
            }
        }
    }

    private fun rotate(instruction: String, value: Int){
        var amount = value
        if(instruction == "L"){
            amount = 360 - value
        }

        for(i in 0 until amount/90){
            rotate90degreesClockWise()
        }
    }

    private fun rotate90degreesClockWise(){
        val temp = east
        east = north
        north = temp * -1
    }
}