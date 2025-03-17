package com.example.jokesapp.data

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.jokesapp.models.JokeModel
import java.io.File
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper


class ViewModelJSON(context: Context) : JokesViewModelInterface, ViewModel()
{
    val appContext = context
    override var jokesList: MutableList<JokeModel> = mutableStateListOf()

    override var jokeSearchResult: MutableList<JokeModel> = mutableStateListOf()

    fun loadJokesFromJSON() {
        Log.d("ViewModelJSON", "Loading jokes from JSON")

        val file = File(appContext.filesDir, "jokes.json")
        val mapper = ObjectMapper()

        if (file.exists()) {
            // Read the entire list of jokes from the JSON file
            val newJokes = mapper.readValue(file, Array<JokeModel>::class.java).toMutableList()

            // Remove all jokes from the list and add the new jokes
            jokesList.clear()
            jokesList.addAll(newJokes)

        }

        Log.d("ViewModelJSON", "Jokes loaded from JSON: ${jokesList.size}")
    }


    fun saveJokesToJSON() {
        Log.d("ViewModelJSON", "Saving jokes to JSON")
        val file = File(appContext.filesDir, "jokes.json")
        val mapper = jacksonObjectMapper()
        try {
            Log.d("ViewModelJSON", "Writing to: ${file.absolutePath}")
            mapper.writeValue(file, jokesList)
            Log.d("ViewModelJSON", "Jokes saved to JSON: ${jokesList.size}")
            if (file.exists()) {
                Log.d("ViewModelJSON", "File exists, size: ${file.length()} bytes")
            } else {
                Log.d("ViewModelJSON", "File does not exist after saving")
            }
        } catch (e: Exception) {
            Log.e("ViewModelJSON", "Error saving jokes to JSON", e)
        }
    }


    override fun getAllJokes() {
        jokesList. clear ()
        loadJokesFromJSON()
    }

    override fun addJoke(joke: JokeModel) {
        val newJoke = joke.copy(id = jokesList.size + 1)
        jokesList.add(newJoke)
        Log.d("ViewModelJSON", "Added joke, list size: ${jokesList.size}")
        saveJokesToJSON()
    }

    override fun removeJoke(joke: JokeModel) {
        jokesList. remove(joke)
        saveJokesToJSON()
    }

    override fun findJokesByKeyword(findPhrase: String) {
        // clear the search result list
        jokeSearchResult.clear()

        // search the jokesList for the findPhrase
        jokesList.forEach { if (it.question.contains(findPhrase) || it.answer.contains(findPhrase))
        {
            jokeSearchResult.add (it)
        }}

    }

    override fun toggleShowJoke(joke: JokeModel) {
        // Determine which list to use
        var jokesList = if (jokeSearchResult.isEmpty()) jokesList else jokeSearchResult

        // Set all answers to not visible
        jokesList.forEach { it.answerIsVisible = false }

        // Set the joke passed in to true
        jokesList[jokesList.indexOf(joke)] = joke.copy(answerIsVisible = true)

    }

}