class Player {
    val name:String  = "Madrigal"
            get() {return field.capitalize()}
    var healthPoints = 89
    var isBlessed = true
    private val isImmortal = false

    fun formatHealthStatus(): String {
        val healthStatus = when (healthPoints) {
            100 -> " is in excellent condition"
            in 90..99 -> " has a few scratches."
            in 75..89 -> if (isBlessed) {
                "has some minor wounds but is healing quite quickly"
            } else {
                "has some minor wounds"
            }
            in 15..74 -> " looks pretty hurt."
            else -> "is in awful condition"
        }
        return healthStatus
    }

    fun auraColor():String{
        var auraVisible = isBlessed && healthPoints > 50 || isImmortal
        val auraColor = if (auraVisible) ("GREEN") else ("RED")
        return auraColor
    }

    fun castFireball(numFireball:Int = 2):String{
        val status = when (numFireball){
            in 1..10 -> "tipsy"
            in 11..20 -> "sloshed"
            in 21..30 -> "soused"
            in 31..40 -> "stewed"
            in 41..50 -> "..t0aSt3d"
            else -> "error"
        }
        return status
    }


}