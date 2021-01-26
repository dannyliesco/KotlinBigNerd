
 fun runSimulation(){
     val greetingFunction = configureGreetingFunction()
     println(greetingFunction("Guyal"))
 }

 fun configureGreetingFunction():(String)->String{
     val structureType = "hospitals"
     var numBuildings = 5
     return { playerName:String ->
         val currentYear = 2018
         numBuildings = 1
         println("Adding $numBuildings $structureType")
         "Welcome to SimVillage, $playerName!(copyright $currentYear)"
     }
 }

fun printConstructionCost(numBuildings:Int){
    val cost = 500
    println("construction cost : ${cost*numBuildings}")
}


 fun main() {
     runSimulation("Guyal") {playerName:String,numBuildings:Int->
         val currentYear = 2018
         println("Adding $numBuildings houses")
         "Welcome to SimVillage, $playerName"
     }
 }

 fun runSimulation(playerName:String,greetingFunction:(String,Int)->String){
     val numBuildings = (1..3).shuffled().last() //randomly select 1,2,or3
     println(greetingFunction(playerName,numBuildings))
 }