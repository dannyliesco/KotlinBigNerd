class Barrel<in T>(item: T){
}

fun main(){
    var fedoraBarrel:Barrel<Fedora> = Barrel(Fedora("a fedora",12))
    var lootbarrel:Barrel<Loot> = Barrel(Coin(13))
    fedoraBarrel = lootbarrel
    fedoraBarrel.run {
        println("Hello")
    }
//    val myFedora:Fedora = lootbarrel.item

}