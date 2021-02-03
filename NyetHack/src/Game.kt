import java.lang.Exception

fun main(args:Array<String>){
    Game.play()
}

object Game {
    private var currentRoom:Room = TownSquare()
    private val player = Player("Hogen")
    private var exit = true
    private val map = listOf(
        listOf(currentRoom,Room("Tavern"),Room("BackRoom")),
        listOf(Room("Long Comidor"),Room("GenericRoom"))
    )

    init {
        println("Welcome to NyetHack")
        player.castFireball()
    }

    fun play(){
        while(exit){
            println(currentRoom.description())
            println(currentRoom.load())
            printPlayerStatus(player)
            print("> Enter your command: ")
            println(GameInput(readLine()).processCommand())
        }
    }


    private fun printPlayerStatus(player:Player){
        println("(Aura:${player.auraColor()})"+
                "(Blessed: ${if (player.isBlessed) "YES" else "NO" })")
        println("${player.name} ${player.formatHealthStatus()}")
    }

    private fun move(directionInput: String){
        try{
            val direction = Direction.valueOf(directionInput.toUpperCase())
            val newPosition = direction.updateCoordinate(player.currentPosition)
            if(!newPosition.isInBounds){
                throw IllegalStateException("$directionInput is out of bounds")
            }

            val newRoom = map[newPosition.x][newPosition.y]
            player.currentPosition = newPosition
            currentRoom = newRoom
            println("OK, you move $directionInput to the ${newRoom.name}.\n ${newRoom.load()}")
        }catch (e:Exception){
            e.printStackTrace()
            "Invalid direction: $directionInput"
        }
    }

    private class GameInput(args:String?){
        private val input = args?:""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1,{" "})

        private fun commandNotFound() = "I'm not quite sure what you're trying to do !"

        fun processCommand() = when(command.toLowerCase()){
            "move" -> move(argument)
            "exit" -> {
                exit = false
                "Exit"
            }
            else -> commandNotFound()
        }
    }
}