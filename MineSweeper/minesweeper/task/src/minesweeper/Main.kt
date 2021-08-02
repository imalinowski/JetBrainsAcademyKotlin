package minesweeper

import kotlin.random.Random

val map = Array(9){CharArray(9){'.'} } // a map
var mines:Int = 0
var fake_mines:Int = 0
fun main() {
    print("How many mines do you want on the field? > ")
    placeMines(readLine()!!.toInt().apply { mines = this })
    calcHints()
    printMap()
    while (mines > 0 && fake_mines > 0){
        print("Set/delete mines marks (x and y coordinates): > ")
        val (x,y) = readLine()!!.split(' ').map { it.toInt() - 1}
        if(map[y][x].isDigit())
            print("There is a number here!")
        else{
            if(map[y][x] == 'X')
                mines -= 1
            else
                fake_mines +=1
            map[y][x] = '*'
            printMap()
        }
    }
}

fun printMap(){
    println(" ?123456789?")
    println("—?—————————?")
    for (i in map.indices) {
        print("${i + 1}?")
        for (j in map[i])
            print(if(j != 'X') j else '.')
        println("?")
    }
    println("—?—————————?")
}

fun placeMines(n:Int){
    for (i in 0 until n) {
        var xy: Pair<Int, Int>
        do {
            xy = Random.nextInt(9) to Random.nextInt(9)
        } while(map[xy.first][xy.second] == 'X')
        map[xy.first][xy.second] = 'X'
    }
}

fun calcHints(){
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
}