package challenges.day7

class LuggageRuleSet(val name: String, val contains: HashSet<ContainSet>) {
    companion object{
        fun fromString(input: String): LuggageRuleSet{
            val sanitizedInput = input.replace(".", "").replace("bags",  "").replace("bag",  "").trim()
            val splits = sanitizedInput.split("contain")
            val name = splits.first().trim()
            val containStrings = splits[1].trim().split(",").map { it.trim() }
            if(containStrings.size == 1 && containStrings.contains("no other")){
                return LuggageRuleSet(name, hashSetOf())
            }

            val contains = containStrings.map { ContainSet(it.substring(1, it.length).trim(),it.substring(0,1).toInt()) }
            return LuggageRuleSet(name, contains.toHashSet())
        }
    }

    fun containsBag(bagName: String): Boolean{
        return !contains.none { it.name == bagName }
    }
}

data class ContainSet(val name: String, val quantity: Int)