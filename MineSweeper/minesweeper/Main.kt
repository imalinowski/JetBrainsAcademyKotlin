package minesweeper

import kotlin.random.Random

val map = Array(9){CharArray(9){'.'} } // a map

fun printMap(){
    for (i in map) {
        for (j in i) print(j)
        println("")
    }
}
fun main() {
    print("How many mines do you want on the field?")
    val n = readLine()!!.toInt()
    for (i in 0 until n) {
        var xy: Pair<Int, Int>
        do {
            xy = Random.nextInt(9) to Random.nextInt(9)
        } while(map[xy.first][xy.second] == 'X')
        map[xy.first][xy.second] = 'X'
    }
    for (i in map.indices){
        for (j in map[i].indices){
            if(map[i][j] == 'X') continue
            var mines = 0
            if (j > 0 && map[i][j - 1] == 'X') mines+=1
            if (map[i][j] == 'X') mines+=1
            if (j < map.size - 1 && map[i][j+1] == 'X') mines+=1
            if (i > 0) {
                if (j > 0 && map[i - 1][j - 1] == 'X') mines+=1
                if (map[i - 1][j] == 'X') mines+=1
                if (j < map.size - 1 && map[i - 1][j+1] == 'X') mines+=1
            }
            if (i < map.size - 1) {
                if (j > 0 && map[i + 1][j - 1] == 'X') mines+=1
                if (map[i + 1][j] == 'X') mines+=1
                if (j < map.size - 1 && map[i + 1][j+1] == 'X') mines+=1
            }
            if(mines > 0) map[i][j] = '0' + mines
        }
    }
    printMap()
}
