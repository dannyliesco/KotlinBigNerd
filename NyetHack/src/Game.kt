class Game {
    companion object {
        @JvmStatic
        fun main(args:Array<String>){
            var player = Player()
            printPlayerStatus(player)
            player.castFireball()
        }
        private fun printPlayerStatus(player:Player){
            val auraColor = player.auraColor()
            val healthStatus = player.formatHealthStatus()
            println("(Aura:$auraColor)"+
                    "(Blessed: ${if (player.isBlessed) "YES" else "NO" })")
            println("${player.name} $healthStatus")
        }
    }
}