import java.lang.Exception
import kotlin.system.exitProcess

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
            GameInput(readLine()).processCommand()
        }
    }
    private fun fight() = currentRoom.monster?.let {
        while(player.healthPoints > 0 && it.healthPoints > 0){
            slay(it)
            Thread.sleep(1000)
        }
        "Combat complete"
    } ?: "There's nothing here to fight"

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
            println("OK, you move $directionInput to the ${currentRoom.name}.\n ${currentRoom.load()}")
        }catch (e:Exception){
            e.printStackTrace()
            "Invalid direction: $directionInput"
        }
    }

    private fun slay(monster: Monster){
        println("${monster.name} did ${monster.attack(player)} damage!")
        println("${player.name} did ${player.attack(monster)} damage!")

        if(player.healthPoints <= 0){
            println(">>>>> You have been defeated ! Thanks for playing.")
            exitProcess(0)
        }

        if(monster.healthPoints <= 0){
            println(">>>> ${monster.name} has been defeated")
            currentRoom.monster = null
        }
    }


    private class GameInput(args:String?){
        private val input = args?:""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1,{" "})

        private fun commandNotFound() = "I'm not quite sure what you're trying to do !"

        fun processCommand() = when(command.toLowerCase()){
            "move" -> move(argument)
            "fight" -> fight()
            "exit" -> {
                exit = false
                "Exit"
            }
            else -> commandNotFound()
        }
    }
}