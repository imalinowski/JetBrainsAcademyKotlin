package search

fun main() {
    println("Enter the number of people:")
    val n = readLine()!!.toInt()
    println("Enter all people:")
    val list = mutableListOf<Person>()
    for (i in 1..n)
        list.add(Person(readLine()!!))
    println()
    println("Enter the number of search queries:")
    val m = readLine()!!.toInt()
    println()
    for (i in 1..m) {
        println("Enter data to search people:")
        val searchRes = list.find(readLine()!!)
        if(searchRes.isNotEmpty()) {
            println("People found")
            for (res in searchRes)
                println(res)
        }
        else println("No matching people found.")
    }
}

fun MutableList<Person>.find(data:String):List<Person>{
    val persons = mutableListOf<Person>()
    for(person in this)
        if(person.info.contains(data, true))
            persons.add(person)
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
