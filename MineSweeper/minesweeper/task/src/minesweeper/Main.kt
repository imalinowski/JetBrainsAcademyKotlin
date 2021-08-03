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
        val (_x,_y,com) = readLine()!!.split(' ')
        val x = _x.toInt() - 1
        val y = _y.toInt() - 1
        when(com) {
            "free" -> {
                if (mines.size == 0) placeMines(n, x to y)
                if (x to y in mines){
                    printMap(true)
                    println("You stepped on a mine and failed!")
                    return
                }
                calcHints(x, y)
            }
            "mine" ->
                if(x to y in marks)
                    marks.remove(x to y)
                else
                    marks.add(x to y)
            else -> print("unknown command")
        }
        printMap(marks == mines)
    }
    println("Congratulations! You found all the mines!")
}

fun printMap(gameEnd:Boolean = false){
    println(" │123456789│")
    println("—│—————————│")
    for (i in map.indices) {
        print("${i + 1}│")
        for (j in map[i].indices)
            print(when {
                    j to i in marks && !gameEnd -> '*'
                    j to i in mines && !gameEnd -> '.'
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
        } while(map[xy.second][xy.first] == 'x' && xy == notXY)
        map[xy.second][xy.first] = 'x'
        mines.add(xy)
    }
}

fun calcHints(x:Int, y:Int) {
    if (map[y][x] == 'x' || map[y][x] == '/') return
    var mines = 0
    for (i in -1..1) // mines count
        if(y + i in map.indices)
            for (j in -1..1)
                if(x + j in map[y + i].indices)
                    if(map[y + i][x + j] == 'x')
                        mines += 1
    marks.remove(x to y)
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