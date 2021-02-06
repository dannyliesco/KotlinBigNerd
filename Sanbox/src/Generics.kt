class LootBox<T:Loot>(vararg _loot:T){
    var open = false
    var loot: Array<out T> = _loot
    fun fetch(item:Int):T?{
        return loot[item].takeIf { open }
    }

    operator fun get(index:Int):T? = loot[index].takeIf { open }

    fun <R> fetch(item:Int,lootModFunction:(T)->R): R?{
        return lootModFunction(loot[item]).takeIf { open }
    }
}

open class Loot(val value:Int)

class Fedora(val name:String,value:Int):Loot(value){
}
class Coin(item: Int):Loot(item)

fun main(){
    val lootBoxOne : LootBox<Fedora> = LootBox(Fedora("a generic-looking fedora.",15),
    Fedora("a other generic-looking fedora",14))

    val lootBoxTwo : LootBox<Coin> = LootBox(Coin(20))

    lootBoxOne.open = true
    lootBoxOne.fetch(1)?.run {
        println("You retrieve $name from the box!")
    }

    val coin  = lootBoxOne.fetch(0){
        Coin(it.value*3)
    }

    coin?.let { println(it.value) }

    val fedora = lootBoxOne[0]
}