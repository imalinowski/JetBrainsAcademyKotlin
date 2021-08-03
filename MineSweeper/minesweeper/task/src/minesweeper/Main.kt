package minesweeper

import kotlin.random.Random

val map = Array(9){CharArray(9){'.'} } // a map
var mines = mutableSetOf<Pair<Int,Int>>()
var marks = mutableSetOf<Pair<Int,Int>>()

fun main() {
    print("How many mines do you want on the field? ")
    val n = readLine()!!.toInt()
    printMap()
    while (marks != mines || mines.size == 0){
        print("Set/delete mines marks (x and y coordinates): ")
        val (x,y,com) = readLine()!!.split(' ')
        when(com) {
            "free" -> {
                if (mines.size == 0) placeMines(n, x.toInt() to y.toInt())
                if (x.toInt() to y.toInt() in mines){

                }
                calcHints(x.toInt(),y.toInt())
            }
            "mine" -> marks.add(x.toInt() to y.toInt())
            else -> print("uknown command")
        }
        printMap()
    }
    println("Congratulations! You found all the mines!")
}

fun printMap(fail:Boolean = false){
    println(" │123456789│")
    println("—│—————————│")
    for (i in map.indices) {
        print("${i + 1}│")
        for (j in map[i].indices)
            print(when {
                    j to i in mines && !fail -> '.'
                    j to i in marks && !fail -> '*'
                    else -> map[i][j] })
        println("│")
    }
    println("—│—————————│")
}

fun placeMines(n:Int,notXY : Pair<Int,Int>){
    for (i in 0 until n) {
        var xy: Pair<Int, Int>
        do {
            xy = Random.nextInt(9) to Random.nextInt(9)
        } while(map[xy.first][xy.second] == 'X' && xy == notXY)
        map[xy.first][xy.second] = 'X'
        mines.add(xy)
    }
}

fun calcHints(x:Int, y:Int) {
    if (map[y][x] == 'X' || map[y][x] == '/') return
    var mines = 0
    for (i in -1..1) // mines count
        if(y + i in map.indices)
            for (j in -1..1)
                if(x + j in map[y + i].indices)
                    if(map[y + i][x + j] == 'X')
                        mines += 1
    if (mines > 0)
        map[y][x] = '0' + mines
    else {
        map[y][x] = '/'
        for (i in -1..1) // recursion
            if(y + i in map.indices)
                for (j in -1..1)
                    if(x + j in map[y + i].indices)
                        calcHints(x + j,y + i)
    }
}