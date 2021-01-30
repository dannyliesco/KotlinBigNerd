import com.sun.xml.internal.fastinfoset.util.StringArray
import java.io.File

const val TAVERN_NAME = "Taernyl's Folly"
const val WINE_GALLON = 5
const val PINT = 0.125

var playerDragonCoin = 5
var patronList = mutableListOf("Eli","Mordoc","Sophie")
var menuList = File("data/tavern-menu-items.txt").readText().split("\n")
var lastName = listOf("Ironfoot","Fernsworth","Baggins")
val uniquePatrons = mutableSetOf<String>()
val partronGold = mutableMapOf<String,Double>()



fun main(args:Array<String>) {



    displayMenu(menuList)

    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first+$last"
        uniquePatrons += name
    }

    uniquePatrons.forEach{
        partronGold[it] = 6.0
    }

    var orderCount = 0
    while(orderCount <= 9){
        placeOrder(uniquePatrons.shuffled().first(),
        menuList.shuffled().first())
        orderCount++
    }
    displayBalance()
}

private fun placeOrder(patronName:String, menu: String){
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speak with $tavernMaster about their order")

    val (type,name,price) = menu.split(',')

    val message = "$patronName buys a $name ($type) for $price"
    println(message)


    performPurchase(price.toDouble(),patronName)

    val phrase = if( name.contains("Dragon's Breath",true)){
        "$patronName exclaims ${toDragonSpeak("Ah, delicious $name!!")} remaing Wine is ${decreaseWine()}"
    }else{
        "$patronName says: Thanks for the $name."
    }
    println(phrase)
}

private fun decreaseWine(): Double {
    return WINE_GALLON - (12*PINT)
}

private fun toDragonSpeak(phrase:String)=phrase.replace(Regex("[aeiouAEIOU]")){
        when(it.value){
            "a","A"->"4"
            "e","E"->"3"
            "i","I"->"1"
            "o","O"->"0"
            "u","U"->"|_|"
            else -> it.value
        }
}

fun performPurchase(price:Double,patronName: String){
    val taotalPurse = partronGold.getValue(patronName)

    if(taotalPurse - price < 0){
        uniquePatrons.remove(patronName)
        return
    }
    partronGold[patronName] = taotalPurse - price
}

private fun displayBalance() {
    partronGold.forEach { patron, balance ->
        println("$patron's purchase balance: Balance: ${"%.2f".format(balance)}")
    }
}

private fun displayMenu(menu:List<String>){
    val welcome = "*** Welcome to Taernyl's Folly ***"
    val lineWidth = welcome.length

    println(welcome)
    menu.forEach {
        val items = it.split(',')
        var showMesage:String
        showMesage = items[1].padEnd(lineWidth-items[2].length,'*')+items[2]
        println(showMesage)
        showMesage = "~[${items[0]}]~"
        showMesage = showMesage.padStart(showMesage.length+(lineWidth - "~[${items[0]}]~".length)/2).padEnd(lineWidth)
        println(showMesage)
    }
}



