import com.sun.webkit.event.WCInputMethodEvent
import kotlin.math.roundToInt

const val TAVERN_NAME = "Taernyl's Folly"
const val WINE_GALLON = 5
const val PINT = 0.125

var playerDragonCoin = 5
var playerGold = 10
var playerSilver = 10

fun main(args:Array<String>) {
    println(toDragonSpeak("aeiou"))
    placeOrder("shandy,DRAGON'S BREATH :IT'S GOT WHAT ADVANTURES CRAVE,5.91")
    placeOrder("elixir,Shirley's Temple,4.12")
}

private fun placeOrder(menu:String){
    val indexOfApostrophe = TAVERN_NAME.indexOf('\'')
    val tavernMaster = TAVERN_NAME.substring(0 until indexOfApostrophe)
    println("Madrigal speak with $tavernMaster about their order")

    val (type,name,price) = menu.split(',')

    val message = "Madrigal buys a $name ($type) for $price"
    println(message)

    performPurchase(price.toDouble())

    val phrase = if( name.contains("Dragon's Breath",true)){
        "Madrigal exclaims ${toDragonSpeak("Ah, delicious $name!!")} remaing Wine is ${decreaseWine()}"
    }else{
        "Madrigal syas: Thanks for the $name."
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
    val totalPurse = playerGold + (playerSilver/100.0)
    println("Total purse: $totalPurse")
    println("Purchase item for $price")

    val remainingBalance = totalPurse - price
    if( remainingBalance < 0){
        println("Remaining balance is not enough!")
        return
    }

    println("Remaining balance: ${"%.2f".format(remainingBalance)}")

    val remainingGold = remainingBalance.toInt()
    val remainingSilver = (remainingBalance%1*100).roundToInt()
    playerGold = remainingGold
    playerSilver = remainingSilver
    displayBalance()
}

private fun displayBalance() {
    println("Player's purchase balance: Gold: $playerGold ,Silver: $playerSilver")
}


