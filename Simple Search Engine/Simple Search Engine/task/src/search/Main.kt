package search

import java.io.File

val persons = mutableListOf<Person>()
val dataII = mutableMapOf<String,MutableList<Int>>()
fun main(args:Array<String>) {

    for ((i,line) in File(args[1]).readLines().withIndex()){
        persons.add(Person(line))
        for (word in line.split(" ")){
            if(word !in dataII)
                dataII[word] = mutableListOf(i)
            else dataII[word]?.add(i)
        }
    }

    while(true) {
        println(
            "\n===Menu===\n" +
            "1. Find a person\n" +
            "2. Print all people\n" +
            "0. Exit"
        )
        when(readLine()!!.toInt()){
            0 -> break
            1 -> {
                println("Select a matching strategy: ALL, ANY, NONE")
                when(readLine()!!.uppercase()){
                    "ALL" -> {
                        println("Enter a name or email to search all suitable people.")
                        val res = persons.findALL(readLine()!!)
                        println("${res.size} persons found")
                        res.forEach{ println(it)}
                    }
                    "ANY" -> {
                        findPersonANY()
                    }
                    "NONE"-> {

                    }
                }
                findPersonANY()
            }
            2 -> {
                println("=== List of people ===")
                for (p in persons) println(p.info)
            }
            else -> print("Incorrect option! Try again.")
        }
    }
    print("Bye!")
}

fun findPersonANY(){
    println("Enter a name or email to search all suitable people.")
    val searchRes = dataII[readLine()!!]
    if(searchRes != null) {
        println("People found")
        for (res in searchRes)
            println(persons[res])
    }
    else println("No matching people found.")
}

fun MutableList<Person>.findALL(data:String):List<Person>{
    val persons = mutableListOf<Person>()
    for(person in this) {
        var check = true
        for (key in data.split(" "))
            check = (person.info.contains(key, true)) && check
        if(check) persons.add(person)
    }
    return  persons
}

data class Person (val info:String){
    override fun toString(): String {
        return info
    }
}

/*
Dwight Joseph djo@gmail.com
Rene Webb webb@gmail.com
Katie Jacobs
Erick Harrington harrington@gmail.com
Myrtle Medina
Erick Burgess
 */
