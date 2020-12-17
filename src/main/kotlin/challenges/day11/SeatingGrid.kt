package challenges.day11

import kotlin.math.min

class SeatingGrid(var coordinates: HashMap<String, String>, val height: Int, val length: Int) {

    companion object{
        fun fromInput(input: List<String>): SeatingGrid{
            val height = input.size
            val length = input.first().length
            val tempCoordinates = hashMapOf<String, String>()
            for (row in input.withIndex()){
                for(column in row.value.map { it.toString() }.withIndex()){
                    tempCoordinates["${column.index}:${row.index}"] = column.value
                }
            }
            return SeatingGrid(tempCoordinates, height, length)
        }
    }

    fun getPosition(x: Int, y: Int): String{
        return coordinates["$x:$y"] ?: throw Exception()
    }

    fun stepPart1(): Boolean{
        val newState = HashMap(coordinates)
        var change = false

        for (point in coordinates){
            if(point.value == ".") continue

            val x = point.key.split(":")[0].toInt()
            val y = point.key.split(":")[1].toInt()

            val occupied = calculateAdjacentOccupied(x, y)
            if(point.value == "L"){
                if(occupied == 0){
                    newState["$x:$y"] = "#"
                    change = true
                }
            }
            else if(point.value == "#"){
                if(occupied >= 4){
                    newState["$x:$y"] = "L"
                    change = true
                }
            }
        }

        coordinates = newState
        return change
    }

    fun stepPart2(): Boolean{
        val newState = HashMap(coordinates)
        var change = false

        for (point in coordinates){
            if(point.value == ".") continue

            val x = point.key.split(":")[0].toInt()
            val y = point.key.split(":")[1].toInt()

            val occupied = calculateAllDirectionsOccupied(x, y)
            if(point.value == "L"){
                if(occupied == 0){
                    newState["$x:$y"] = "#"
                    change = true
                }
            }
            else if(point.value == "#"){
                if(occupied >= 5){
                    newState["$x:$y"] = "L"
                    change = true
                }
            }
        }

        coordinates = newState
        return change
    }

    private fun calculateAdjacentOccupied(x: Int, y: Int): Int{
        var occupied = 0

        if(x != length -1){
            if(getPosition(x + 1, y) == "#") occupied++
            if(y != height - 1 && getPosition(x + 1, y + 1) == "#") occupied++
            if(y != 0 && getPosition(x + 1, y - 1) == "#") occupied++
        }

        if(x != 0){
            if(getPosition(x - 1, y) == "#") occupied++
            if(y != height - 1 && getPosition(x - 1, y + 1) == "#") occupied++
            if(y != 0 && getPosition(x - 1, y - 1) == "#") occupied++
        }

        if(y != 0 && getPosition(x, y - 1) == "#") occupied++
        if(y != height -1 && getPosition(x, y + 1) == "#") occupied++


        return occupied
    }

    private fun calculateAllDirectionsOccupied(x: Int, y: Int): Int{
        var occupied = 0

        //N
        var done = false
        var iterator = 1
        while(y - iterator >= 0 && !done){
            val element = getPosition(x, y - iterator)
            if(element == "L") done = true
            else if(element == "#"){
                occupied++
                done = true
            }
            iterator++
        }

        //S
        done = false
        iterator = 1
        while(y + iterator < height && !done){
            val element = getPosition(x, y + iterator)
            if(element == "L") done = true
            else if(element == "#"){
                occupied++
                done = true
            }
            iterator++
        }

        //E
        done = false
        iterator = 1
        while(x - iterator >= 0 && !done){
            val element = getPosition(x - iterator, y)
            if(element == "L") done = true
            else if(element == "#"){
                occupied++
                done = true
            }
            iterator++
        }

        //W
        done = false
        iterator = 1
        while(x + iterator < length && !done){
            val element = getPosition(x + iterator,y)
            if(element == "L") done = true
            else if(element == "#"){
                occupied++
                done = true
            }
            iterator++
        }

        //NE
        done = false
        iterator = 1
        while(x - iterator >= 0 && y - iterator >= 0 && !done){
            val element = getPosition(x - iterator, y - iterator)
            if(element == "L") done = true
            else if(element == "#"){
                occupied++
                done = true
            }
            iterator++
        }

        //NW
        done = false
        iterator = 1
        while(x + iterator < length && y - iterator >= 0 && !done){
            val element = getPosition(x + iterator, y - iterator)
            if(element == "L") done = true
            else if(element == "#"){
                occupied++
                done = true
            }
            iterator++
        }

        //SE
        done = false
        iterator = 1
        while(x - iterator >= 0 && y + iterator < height  && !done){
            val element = getPosition(x - iterator, y + iterator)
            if(element == "L") done = true
            else if(element == "#"){
                occupied++
                done = true
            }
            iterator++
        }

        //SW
        done = false
        iterator = 1
        while(x + iterator < length  && y + iterator < height  && !done){
            val element = getPosition(x + iterator, y + iterator)
            if(element == "L") done = true
            else if(element == "#"){
                occupied++
                done = true
            }
            iterator++
        }

        return occupied
    }

    fun getAmountOccupied(): Int{
        return coordinates.values.filter { it == "#" }.size
    }
}