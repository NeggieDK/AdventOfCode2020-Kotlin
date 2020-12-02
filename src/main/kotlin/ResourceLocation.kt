const val path = "C:\\Users\\Aaron\\IdeaProjects\\AdventOfCode2020-Kotlin\\src\\main\\resources\\"

fun getResourcePath(filename: String): String{
    return "${path}\\${filename}"
}