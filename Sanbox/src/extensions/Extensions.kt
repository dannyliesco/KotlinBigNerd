package extensions

fun String.addEnthusiasm(amount:Int) = this + "!".repeat(amount)
fun <T> T.easyprint():T{
    println(this)
    return this
}


/*扩展出一个类的属性 numVowels*/
val String.numVowels
    get() = count{"aeiof".contains(it)}

/*可空扩展*/
infix fun String?.printwithDefault(default:String) = print( this ?:default)

fun main() {
    println("This is a string extensions test".easyprint().addEnthusiasm(10).easyprint().numVowels)
    val stringNull:String? = null

    stringNull.printwithDefault("Default Value")
    /*有了infix关键字就可以使用去掉 . 和 () 直接调用*/
    stringNull printwithDefault "Default value"
}