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
    }

    private fun useButtonFlatMap() = with(binding) {
        val genreList = listOf("Action", "Comedy", "Drama", "Action", "Comedy")
        val genreList2 = listOf("ac", "co", "dr", "sci", "cr")
        val genresForFlatMap : List<GenreUIModel> = listOf(GenreUIModel(genreList), GenreUIModel(genreList2) )
        bFlatMap.setOnClickListener {
            tvMain.text = genresForFlatMap.flatMap { it.genres }.toString()
        }
    }

    private fun useButtonMap() = with(binding) {
        val genreList = listOf("Action", "Comedy", "Drama", "Action", "Comedy")
        val genreList2 = listOf("ac", "co", "dr", "sci", "cr")
        val genresForMap : List<GenreUIModel> = listOf(GenreUIModel(genreList), GenreUIModel(genreList2) )
        bMap.setOnClickListener {
            tvMain.text = genresForMap.map { it.genres }.toString()
        }
    }

    private fun useButtonMapWithFlatten() = with(binding) {
        val genreList = listOf("Action", "Comedy", "Drama", "Action", "Comedy")
        val genreList2 = listOf("ac", "co", "dr", "sci", "cr")
        val genresForMapWithFlatten : List<GenreUIModel> = listOf(GenreUIModel(genreList), GenreUIModel(genreList2) )
        bMapWithFlatten.setOnClickListener {
            tvMain.text = genresForMapWithFlatten.map { it.genres }.flatten().toString()
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
            tvMain.text = genre
        }
    }

    private fun useButtonJoinToString() = with(binding) {
        val genreListForJoinToString = listOf("Action", "Comedy", "Drama", "Action", "Comedy")
        bJoinToString.setOnClickListener {
            tvMain.text = genreListForJoinToString.joinToString(", ")
        }
    }

    private fun useButtonJoinToStringWithDistinct() = with(binding) {
        val genreListForJoinToStringWithDistinct = listOf("Action", "Comedy", "Drama", "Action", "Comedy")
        bJoinToStringWithDistinct.setOnClickListener {
            tvMain.text = genreListForJoinToStringWithDistinct.distinct().joinToString(", ")
        }
    }
}