import java.lang.Exception
import java.lang.IllegalStateException

fun main() {
    var swordsJuggling: Int ? = null
    val isJugglingProficient = (1..3).shuffled().last() == 3
    if( isJugglingProficient){
        swordsJuggling = 2
    }

    try {
        proficiencyChcek(swordsJuggling)
    }catch (e:Exception){
        e.printStackTrace()
    }

    swordsJuggling = swordsJuggling!!.plus(1)

    println("You juggle $swordsJuggling swords!")
}

fun proficiencyChcek(swordsJuggling:Int?){
//    swordsJuggling ?: throw UnskilledSwordJugglerException()
    checkNotNull(swordsJuggling,{"Player cannot juggle swords"})
}

class UnskilledSwordJugglerException():
    IllegalStateException("Player cannot juggle swords")