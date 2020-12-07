const val path = "C:\\Users\\aarondk\\Kotlin Projects\\AdventOfCode2020-Kotlin\\src\\main\\resources"

fun getResourcePath(filename: String): String{
    return "${path}\\${filename}"
}