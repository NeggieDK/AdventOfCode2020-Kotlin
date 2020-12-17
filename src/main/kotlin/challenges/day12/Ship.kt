package challenges.day12

import kotlin.math.abs

class Ship(var east: Int, var north: Int) {

    private var direction = 0;

    fun move(instruction: String, value: Int){
        if(instruction == "L" || instruction == "R"){
            changeDirection(instruction, value)
            return
        }

        if(instruction == "F"){
            move(getDirection(), value)
            return
        }

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

    fun getDirection(): String{
        while(direction >= 360){
            direction -= 360
        }

        while(direction < 0){
            direction += 360
        }

        when (direction) {
            0 -> return "E"
            90 -> return "N"
            180 -> return "W"
            270 -> return "S"
            else -> return ""
        }
    }

    private fun changeDirection(instruction: String, value: Int){
        if(instruction == "L"){
            direction += value
        }
        else{
            direction -= value
        }
    }

    fun getManhattanDistance(): Int{
        return abs(east) + abs(north)
    }
}