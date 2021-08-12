package search

import java.io.File

val persons = mutableListOf<Person>()
val dataII = mutableMapOf<String,MutableList<Int>>()
fun main(args:Array<String>) {

    for ((i,line) in File(args[1]).readLines().withIndex()){
        persons.add(Person(line))
        for (word in line.split(" ").map { it.lowercase() }){
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
                val strategy = readLine()!!.uppercase()
                println("Enter a name or email to search all suitable people.")
                val key = readLine()!!
                val res : Set<Person> = when(strategy){
                    "ALL" -> findPersonALL(key)
                    "ANY" -> findPersonANY(key)
                    "NONE"-> findPersonNONE(key)
                    else -> {
                        println("wrong cmd")
                        emptySet()
                    }
                }
                if(res.isEmpty()) {
                    println("No matching people found.")
                    continue
                }
                println("${res.size} persons found")
                for(person in res)
                    println(person.info)
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

fun findPersonANY(key:String):Set<Person>{
    val searchRes = mutableSetOf<Int>().apply {
        key.split(" ").forEach{
            val p = dataII[it]?.toList()
            if( p != null)
                addAll(p)
        }
    }
    return mutableSetOf<Person>().apply {
        searchRes.forEach{ add(persons[it])}
    }
}

fun findPersonALL(key:String):Set<Person>{
    var searchRes = mutableSetOf<Int>()
    key.split(" ").forEach{
        val p = dataII[it]?.toList()
        if( p != null)
            searchRes = searchRes.intersect(p).toMutableSet()
    }
    return mutableSetOf<Person>().apply {
        searchRes.forEach{ add(persons[it])}
    }
}

fun findPersonNONE(key:String):Set<Person>{
    val searchRes = mutableSetOf<Int>().apply {
        for (i in persons.indices) add(i)
    }
    key.split(" ").forEach{
        dataII[it]?.toList()?.forEach { index ->
            searchRes.remove(index)
        }
    }
    return mutableSetOf<Person>().apply {
        searchRes.forEach{ add(persons[it])}
    }
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
