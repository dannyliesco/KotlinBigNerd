import kotlin.system.measureNanoTime

private fun Int.isPrime():Boolean{
    ( 2 until this).map {
        if(this%it == 0){
            return false
        }
    }
    return true
}

fun main() {
    //transform
    val animals = listOf("zebra", "giraffe", "elephant", "rat")
    val babies = animals.map { animal -> "A baby${animal}" }.map{
            baby -> "$baby,with the cutest little tail ever!"
    }
    println(babies)

    //
    val tenDollarWords = listOf("auspicious","avuncular","obviate")
    val tenDollarLength = tenDollarWords.map {
        it.length
    }
    println(tenDollarLength)

    //filter
    println(listOf(listOf(1,2,3), listOf(4,5,6)).flatMap {
        it
    })

    val itemsOfManyColors = listOf(listOf("red apple","green apple","blue apple"),
    listOf("red fish","blue fish"),listOf("yellow banana","teal banana"))

    val redItems = itemsOfManyColors.flatMap {
        it.filter { it.contains("red") }
    }
    println(redItems)

    //combines
    //why functional program
    val employees =  listOf("Denny","Claudette","Petter")
    val shirtSize = listOf("large","x-large","medium")
    val employeeShirtSizes = employees.zip(shirtSize).toMap().map {
        "${it.key},shirt size: ${it.value}"
    }


    val folderValue = listOf(1,2,3,4).fold(0){
        accumulator, number ->
        println("Accumulated value: $accumulator")
        accumulator + (number*3)
    }
    println(folderValue)


    //find 1000 primes
    var listInNanos = measureNanoTime {
        val toList = (0..5000).filter{
            it.isPrime()
        }.take(1000)
    }

    var squenceInNanos = measureNanoTime {
        generateSequence(3){
            value -> value+1
        }.take(1000)
    }

    println("List compeleted in ${listInNanos/10000000.00} ns")
    println("Squence compeleted in ${squenceInNanos/10000000.00} ns")


}



