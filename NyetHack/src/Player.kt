import java.io.File

class Player(_name:String,
             var healthPoints:Int = 100,
             var isBlessed:Boolean,
             private var isImmortal:Boolean){
    var name = _name
        get() = field.capitalize()
        private set(value) {
            field.trim()
        }

    var currentPosition = Coordinate(0,0)

    val hometown by lazy{
        selectTown()
    }

    constructor(name:String):this(name,
        isImmortal = true,
        isBlessed = true
        ){
//        println("second construct")
    }

    init {
//        println("init block")
        require(healthPoints > 0,{"HealthPoints must be greater than zero."})
        require(name.isNotBlank(),{"Player must have a name"})
    }

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

    private fun selectTown() = File("data/towns.txt")
        .readText()
        .split("\n")
        .shuffled()
        .first()



}