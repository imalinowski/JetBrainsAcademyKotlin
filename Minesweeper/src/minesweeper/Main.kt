package minesweeper

import kotlin.random.Random

val map = Array(9){CharArray(9){'.'} }

fun printMap(){
    for ((ind,i) in map.withIndex()) {
        //print("$ind ")
        for (j in i) print(j)
        println("")
    }
}
fun main() {
    print("How many mines do you want on the field?")
    val n = readLine()!!.toInt()
    for (i in 0 until n) {
        var xy = Random.nextInt(9) to Random.nextInt(9)
        while(map[xy.first][xy.second] == 'X')
            xy = Random.nextInt(9) to Random.nextInt(9)
        map[xy.first][xy.second] = 'X'
    }
    printMap()
}
