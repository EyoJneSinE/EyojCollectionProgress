package com.eniskaner.eyojcollectionprogress.collection

fun main() {
    data class Person(val name: String, val age: Int)

    val personList = listOf(Person("Eyoj", 45),
        Person("Seb", 31),
        Person("Kon", 25),
        Person("Div", 11))

    println(personList.associateBy{
        it.age
    })

    println(personList.groupBy{
        it.name
    })

    val (over30, under30) = personList.partition {
        it.age > 30
    }

    println(over30)
    println(under30)
    println()

    val numbers = listOf(-3, 7, 2, -10, 1)

    println(numbers.flatMap {
        listOf(it * 10, it + 10)
    })
    println()

    println(numbers.flatMapIndexed { index: Int, i: Int ->
        listOf(i * index)
    })
    println()
    data class Genre(val genres: List<String>)
    val genreList = listOf("Action", "Comedy", "Drama", "Action", "Comedy")
    val genreList2 = listOf("ac", "co", "dr", "sci", "cr")

    val genres : List<Genre> = listOf(Genre(genreList), Genre(genreList2) )
    println("1")
    println(genres)
    println("2")
    println(genres.map { it.genres })
    println("3")
    println(genres.map { it.genres }.flatten())
    println("4")
    println(genres.flatMap { it.genres })
    println("5")
    println(genres.map { it.genres })
    println("6")

    val genre = Genre(genreList)
    val genreName = genreList.map {
        it
    }
    println(genre.genres)
    println(genre.genres.distinct())
    println()
    genreName.forEach { println(it) }
    println()
    println(genre.genres.joinToString(", "))
    println()
    println(numbers.getOrElse(1) {50})
    println(numbers.getOrElse(5) {50})

    val names = listOf("A","B","C","D")
    println(numbers zip names)

}