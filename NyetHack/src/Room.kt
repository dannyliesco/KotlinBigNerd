open class Room(val name:String){
    protected open val dangerLevel = 5
    var monster : Monster ?= Goblin()

    fun description() = "Room: $name\n"+
                "Danger level : $dangerLevel\n"+
            "Creature: ${monster?.description?:"none"}"
    open fun load() = "Nothing much here ..."
}

class TownSquare:Room("Town Suare"){
    private var bellSound = "GWONG"
    override val dangerLevel = super.dangerLevel - 3

    override fun load() = "The villages rally and cheers up to you enter \n!${ringBell()}"

    private fun ringBell() = "The bell tower announces your arrival. $bellSound"
}