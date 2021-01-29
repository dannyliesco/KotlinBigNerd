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

fun main(args:Array<String>) {

    displayMenu(menuList)
    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first+$last"
        uniquePatrons += name
    }

    var orderCount = 0
    while(orderCount <= 9){
        placeOrder(uniquePatrons.shuffled().first(),
        menuList.shuffled().first())
        orderCount++
    }
//
//    patronList.forEachIndexed { index, s ->
//        println("Good evening, $s your're #${index+1} in line")
//        placeOrder(s,uniquePatrons.shuffled().first())
//    }
}

private fun placeOrder(patronName:String, menu: String){
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("$patronName speak with $tavernMaster about their order")

    val (type,name,price) = menu.split(',')

    val message = "$patronName buys a $name ($type) for $price"
    println(message)

//    performPurchase(price.toDouble())

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

fun performPurchase(price:Double){
    displayBalance()
//    val totalPurse = playerGold + (playerSilver/100.0)
    val totalPurse = playerDragonCoin*4.17
    println("Total purse: $totalPurse")
    println("Purchase item for $price")

    val remainingBalance = totalPurse - price
    if( remainingBalance < 0){
        println("Remaining balance is not enough!")
        return
    }

    println("Remaining balance: ${"%.2f".format(remainingBalance)}")

    displayBalance()
}

private fun displayBalance() {
    println("Player's purchase balance: DragonGold: $playerDragonCoin")
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

