import java.io.File

class Player(_name:String,
             var healthPoints:Int = 100,
             var isBlessed:Boolean,
             private var isImmortal:Boolean){
    var name = _name
        get() = "${field.capitalize()} of $hometown"
        private set(value) {
            field = value.trim()
        }

    val hometown by lazy{ selectTown() }

    var currentPosition = Coordinate(0,0)

    init {
        require(healthPoints > 0,{"HealthPoints must be greater than zero."})
        require(name.isNotBlank(),{"Player must have a name"})
    }

    constructor(name:String) : this(name, isImmortal = true, isBlessed = true){
        if( name.toLowerCase() == "kar") healthPoints = 40
    }

    fun auraColor():String{
        var auraVisible = isBlessed && healthPoints > 50 || isImmortal
        val auraColor = if (auraVisible) ("GREEN") else ("RED")
        return auraColor
    }

    fun formatHealthStatus() = when (healthPoints) {
        100 -> "is in excellent condition"
        in 90..99 -> "has a few scratches."
        in 75..89 -> if (isBlessed) {
            "has some minor wounds but is healing quite quickly"
        } else {
            "has some minor wounds"
        }
        in 15..74 -> "looks pretty hurt."
        else -> "is in awful condition"
    }

    fun castFireball(numFireball:Int = 2)=
        println("A glass of fireball springs into existence. (x$numFireball)")

    private fun selectTown() = File("data/towns.txt")
        .readText()
        .split("\n")
        .shuffled()
        .first()

}