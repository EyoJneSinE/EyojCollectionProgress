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
        useButtonJoinToString()
        useButtonFlatMap()
        useButtonMap()
        useButtonMapWithFlatten()
    }

    private fun useButtonJoinToString() = with(binding) {
        val genreList = listOf("Action", "Comedy", "Drama", "Action", "Comedy")
        genresList.addAll(genreList)
        bJoinToString.setOnClickListener {
            tvMain.text = genresList.joinToString(", ")
        }
    }

    private fun useButtonFlatMap() = with(binding) {
        val genreList = listOf("Action", "Comedy", "Drama", "Action", "Comedy")
        val genreList2 = listOf("ac", "co", "dr", "sci", "cr")
        val genres : List<GenreUIModel> = listOf(GenreUIModel(genreList), GenreUIModel(genreList2) )
        genresList.addAll(genreList)
        bFlatMap.setOnClickListener {
            tvMain.text = genres.flatMap { it.genres }.toString()
        }
    }

    private fun useButtonMap() = with(binding) {
        val genreList = listOf("Action", "Comedy", "Drama", "Action", "Comedy")
        val genreList2 = listOf("ac", "co", "dr", "sci", "cr")
        val genres : List<GenreUIModel> = listOf(GenreUIModel(genreList), GenreUIModel(genreList2) )
        genresList.addAll(genreList)
        bMap.setOnClickListener {
            tvMain.text = genres.map { it.genres }.toString()
        }
    }

    private fun useButtonMapWithFlatten() = with(binding) {
        val genreList = listOf("Action", "Comedy", "Drama", "Action", "Comedy")
        val genreList2 = listOf("ac", "co", "dr", "sci", "cr")
        val genres : List<GenreUIModel> = listOf(GenreUIModel(genreList), GenreUIModel(genreList2) )
        genresList.addAll(genreList)
        bMapWithFlatten.setOnClickListener {
            tvMain.text = genres.map { it.genres }.flatten().toString()
        }
    }

    private fun useButtonForEach() = with(binding) {
        val genreList = listOf("Action", "Comedy", "Drama", "Action", "Comedy")
        bForEach.setOnClickListener {
            genreList.forEach {
                tvMain.text = it
            }
        }
    }

    fun getData() {
        val genreList = listOf("Action", "Comedy", "Drama", "Action", "Comedy")
        val genreList2 = listOf("ac", "co", "dr", "sci", "cr")
        genresList.addAll(genreList)

        val genres : List<GenreUIModel> = listOf(GenreUIModel(genreList), GenreUIModel(genreList2) )

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

        val genre = GenreUIModel(genreList)
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

    }
}