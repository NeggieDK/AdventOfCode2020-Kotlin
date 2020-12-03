package challenges.day3

class Grid {

    private var grid = HashMap<String, String>()
    var length = 0
    var height = 0


    fun parseFromString(strList: List<String>){
        height = strList.size
        length = strList[0].length

        for(line in strList.withIndex()){
            for(element in line.value.withIndex()){
                grid["${element.index}:${line.index}"] = element.value.toString()
            }
        }
    }

    fun getLocation(x: Int, y: Int): String{
        return grid["${x}:${y}"] ?: ""
    }
}