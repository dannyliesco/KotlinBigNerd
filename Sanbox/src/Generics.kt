class LootBox<T>(var _loot:T){
    var open = false
    var loot = _loot
    fun fetch():T?{
        return loot.takeIf { open }
    }

    fun <R> fetch(lootModFunction:(T)->R): R?{
        return lootModFunction(loot).takeIf { open }
    }
}

class Fedora(val name:String,val value:Int)
class Coin(val value: Int)

fun main(){
    val lootBoxOne : LootBox<Fedora> = LootBox(Fedora("a generic-looking fedora.",15))
    val lootBoxTwo : LootBox<Coin> = LootBox(Coin(20))

    lootBoxOne.open = true
    lootBoxOne.fetch()?.run {
        println("You retrieve $name from the box!")
    }

    val coin  = lootBoxOne.fetch({
        Coin(it.value*3)
    })

    coin?.let { println(it.value) }
}