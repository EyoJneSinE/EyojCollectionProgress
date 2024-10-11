package com.eniskaner.eyojointostringcollection

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.eniskaner.eyojointostringcollection.data.model.GenreUIModel
import com.eniskaner.eyojointostringcollection.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val genresList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.tvMain.text = "You will see the change when you use button"
        useButtonFlatMap()
        useButtonMap()
        useButtonMapWithFlatten()
        useButtonForEach()
        useButtonJoinToString()
        useButtonJoinToStringWithDistinct()
        useButtonCapitalizeSimpleWay()
    }

    private fun useButtonFlatMap() = with(binding) {
        val genreList = listOf("Action", "Comedy", "Drama", "Action", "Comedy")
        val genreList2 = listOf("ac", "co", "dr", "sci", "cr")
        val genresForFlatMap: List<GenreUIModel> =
            listOf(GenreUIModel(genreList), GenreUIModel(genreList2))
        bFlatMap.setOnClickListener {
            tvMain.text = genresForFlatMap.flatMap { it.genres }.toString().removePrefix("[").removeSuffix("]")
        }
    }

    private fun useButtonMap() = with(binding) {
        val genreList = listOf("Action", "Comedy", "Drama", "Action", "Comedy")
        val genreList2 = listOf("ac", "co", "dr", "sci", "cr")
        val genresForMap: List<GenreUIModel> =
            listOf(GenreUIModel(genreList), GenreUIModel(genreList2))
        bMap.setOnClickListener {
            tvMain.text = genresForMap.map { it.genres }.toString().removeSurrounding("[[", "]]")
        }
    }

    private fun useButtonMapWithFlatten() = with(binding) {
        val genreList = listOf("Action", "Comedy", "Drama", "Action", "Comedy")
        val genreList2 = listOf("ac", "co", "dr", "sci", "cr")
        val genresForMapWithFlatten: List<GenreUIModel> =
            listOf(GenreUIModel(genreList), GenreUIModel(genreList2))
        bMapWithFlatten.setOnClickListener {
            tvMain.text = genresForMapWithFlatten.map { it.genres }.flatten().toString().removeSurrounding("[", "]")
        }
    }

    private fun useButtonForEach() = with(binding) {
        val genreListForEach = listOf("Action", "Comedy", "Drama", "Action", "Comedy")
        bForEach.setOnClickListener {
            val genre = buildString {
                genreListForEach.forEach {
                    append(it.plus(", "))
                }
            }
            tvMain.text = genre.removeSuffix(", ")
        }
    }

    private fun useButtonJoinToString() = with(binding) {
        val genreListForJoinToString = listOf("Action", "Comedy", "Drama", "Action", "Comedy")
        bJoinToString.setOnClickListener {
            tvMain.text = genreListForJoinToString.joinToString(", ")
        }
    }

    private fun useButtonJoinToStringWithDistinct() = with(binding) {
        val genreListForJoinToStringWithDistinct =
            listOf("Action", "Comedy", "Drama", "Action", "Comedy")
        bJoinToStringWithDistinct.setOnClickListener {
            tvMain.text = genreListForJoinToStringWithDistinct.distinct().joinToString(", ")
        }
    }

    private fun useButtonCapitalizeSimpleWay() = with(binding) {
        val capitalizeSimpleWayList = capitalizeWithPressRules(NON_CAPITALIZED_WORDS.toString())
        /*bCapitalizeSimpleWay.setOnClickListener {
            tvMain.text = capitalizeSimpleWayList
        }*/
    }

    /*private fun anotherFuncForSeq() = with(binding) {
        val genreListForSeq = listOf("Action", "Comedy", "Drama", "Action", "Comedy")
        genreListForSeq.asSequence().joinToString(", ") { it.replaceFirstChar(Char::uppercaseChar) }
        sequence {
            var startIndex = 0
            while (startIndex < input.length) {
                val endIndex = input.indexOf(' ', startIndex).takeIf { it > 0 } ?: input.length
                yield(input.substring(startIndex, endIndex))
                startIndex = endIndex + 1
            }
        }.joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }
    }*/

    val NON_CAPITALIZED_WORDS = setOf(
        "as",
        "at",
        "but",
        "by",
        "for",
        "in",
        "of",
        "off",
        "on",
        "per",
        "to",
        "up",
        "via", // short prepositions
        "a",
        "an",
        "the", // articles
        "for",
        "and",
        "nor",
        "but",
        "or",
        "yet",
        "so" // coordinating conjunctions
    )

    fun capitalizeSimpleWay(input: String): String = input
        .split(' ')
        .joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

    fun capitalizeWithSequence(input: String): String = sequence {
        var startIndex = 0
        while (startIndex < input.length) {
            val endIndex = input.indexOf(' ', startIndex).takeIf { it > 0 } ?: input.length
            yield(input.substring(startIndex, endIndex))
            startIndex = endIndex + 1
        }
    }.joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

    fun capitalizeWithDoubleSpaces(input: String): String = input
        .split("\\W+".toRegex())
        .joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

    fun capitalizeWithDoubleSpacesAndSequence(input: String): String = sequence {
        var startIndex = 0
        while (startIndex < input.length) {
            val endIndex = input.findFirstSince(startIndex) { it == ' ' }
            yield(input.substring(startIndex, endIndex))
            startIndex = input.findFirstSince(endIndex) { it != ' ' }
        }
    }.joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }

    fun capitalizeWithPressRules(input: String): String {
        val components = input.split("\\W+".toRegex())
        return buildString {
            components.forEachIndexed { index, word ->
                when (index) {
                    in 1..components.size - 2 -> word.capitalizeMiddleWord() // Some short auxiliary words aren't capitalized
                    else -> word.replaceFirstChar(Char::uppercaseChar) // The first and the last words are always capitalized
                }.let { append(it).append(' ') }
            }
            deleteCharAt(length - 1) // Drop the last whitespace
        }
    }

    private fun String.capitalizeMiddleWord(): String =
        if (length > 3 || this !in NON_CAPITALIZED_WORDS) replaceFirstChar(Char::uppercaseChar) else this

    fun String.findFirstSince(position: Int, test: (Char) -> Boolean): Int {
        for (i in position until length) {
            if (test(this[i])) return i
        }
        return length
    }
}