open class Room(val name:String){
    protected open val dangerLevel = 5

    fun description() = "Room: $name\n"+
                "Danger level : $dangerLevel"

    open fun load() = "Nothing much here ..."
}

class TownSquare:Room("Town Suare"){
    private var bellSound = "GWONG"
    override val dangerLevel = super.dangerLevel - 3

    override fun load(): String  = "The villages rally and cheers up to you enter \n!${ringBell()}"

    private fun ringBell() = "The bell tower announces your arrival. $bellSound"
}