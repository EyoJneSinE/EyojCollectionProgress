package com.eniskaner.eyojcollectionprogress.collection

fun main() {
    println()
    println()
    println()
    data class Person(val name: String, val age: Int)

    val personList = listOf(Person("Eyoj", 45),
        Person("Seb", 31),
        Person("Kon", 25),
        Person("Div", 11))

    println("associateBy")
    println(personList.associateBy{
        it.age
    })
    println()

    println("associate")
    println(personList.associate {
        it.name to it.age
    })
    println()

    println("associateWith")
    println(personList.associateWith {
        it.age
    })
    println()

    println("associateBy")
    println(personList.associateBy {
        it.name
    })
    println()

    println("groupBy")
    println(personList.groupBy{
        it.name
    })
    println()

    println("partition")
    val (over30, under30) = personList.partition {
        it.age > 30
    }
    println()

    println("print users over 30")
    println(over30)
    println()

    println("print users under 30")
    println(under30)
    println()

    val numbers = listOf(-3, 7, 2, -10, 1)

    println("flatMap")
    println(numbers.flatMap {
        listOf(it * 10, it + 10)
    })
    println()

    println("flatMapIndexed")
    println(numbers.flatMapIndexed { index: Int, i: Int ->
        listOf(i * index)
    })
    println()


    data class Genre(val genres: List<String>)
    val genreList = listOf("Action", "Comedy", "Drama", "Action", "Comedy")
    val genreList2 = listOf("ac", "co", "dr", "sci", "cr")

    val genres : List<Genre> = listOf(Genre(genreList), Genre(genreList2) )

    println("Just Print genres list")
    println(genres)
    println()

    println("print genres in genres list")
    println(genres.map { it.genres })
    println()

    println("print genres in genres list with map and flatten")
    println(genres.map { it.genres }.flatten())
    println()

    println("print genres in genres list with flatMap")
    println(genres.flatMap { it.genres })
    println()

    println("print genres in genres list")
    println(genres.map { it.genres })
    println()

    val genre = Genre(genreList)
    val genreName = genreList.map {
        it
    }

    println("print genre list")
    println(genre.genres)
    println()

    println("print genre list with distinct")
    println(genre.genres.distinct())
    println()

    println("print genre name with forEach")
    genreName.forEach { println(it) }
    println()

    println("print genre list with joinToString")
    println(genre.genres.joinToString(", "))
    println()

    println("print getOrElse with condition index in of range")
    println(numbers.getOrElse(1) {50})
    println()

    println("print getOrElse with condition index out of range")
    println(numbers.getOrElse(5) {50})
    println()

    println("print zip with numbers and names")
    val names = listOf("A","B","C","D")
    println(numbers zip names)
    println()

}