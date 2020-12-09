package challenges.day8

import getResourcePath
import java.io.File

fun execute(){
    val instructions = File(getResourcePath("day8.txt")).readLines().map {
        val splits = it.split(" ")
        Instruction(splits[0].trim(), splits[1].trim().toInt()) }
    println(part1(instructions))
    println(part2(instructions))
}

fun part1(instructions: List<Instruction>): Int{
    val usedInstructionIndex = hashSetOf<Int>()
    var accumulator = 0;
    var done = false
    var index = 0

    while(!done){
        if(usedInstructionIndex.contains(index)){
            done = true
            return accumulator
        }

        usedInstructionIndex.add(index)
        val instruction = instructions[index]
        when(instruction.code){
            "acc" -> {
                accumulator += instruction.value
                index++
            }
            "jmp" -> index += instruction.value
            "nop" -> index++
        }
    }

    return 0
}

fun part2(instructions: List<Instruction>): Int{
    for(i in 0..instructions.size){
        val instruction = instructions[i]
        if(instruction.code == "nop"){
            var newInstructions = instructions.toMutableList()
            newInstructions[i] = Instruction("jmp", newInstructions[i].value)

            val acc = tryInstructions(newInstructions)
            if(acc != null){
                return acc
            }
        }
        if(instruction.code == "jmp"){
            var newInstructions = instructions.toMutableList()
            newInstructions[i] = Instruction("nop", newInstructions[i].value)
            tryInstructions(newInstructions)

            val acc = tryInstructions(newInstructions)
            if(acc != null){
                return acc
            }
        }
    }
    return 0
}

fun tryInstructions(instructions: List<Instruction>): Int?{
    val usedInstructionIndex = hashSetOf<Int>()
    var accumulator = 0;
    var index = 0

    while(true){
        if(usedInstructionIndex.contains(index)){
            return null
        }

        if(index == instructions.size){
            return accumulator
        }

        usedInstructionIndex.add(index)
        val instruction = instructions[index]
        when(instruction.code){
            "acc" -> {
                accumulator += instruction.value
                index++
            }
            "jmp" -> index += instruction.value
            "nop" -> index++
        }
    }

    return null
}

