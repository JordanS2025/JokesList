package com.example.jokesapp.screens

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.jokesapp.Joke1
import com.example.jokesapp.models.JokeModel


public @Composable
fun ShowJokesScreen(jokes: MutableList<JokeModel>) {
    LazyColumn {
        items(jokes.size) { index ->
            Joke1(jokeModel = jokes[index]) { it ->
                Log.d("Joke tag", "You clicked joke number: $it")

                // Toggle the visibility property of the joke that was clicked.
                // Notice the ! (negation) character.
                val changedJoke = jokes[it].copy(answerIsVisible = !jokes[it].answerIsVisible)

                // Replace the joke item at position number "it"
                jokes[it] = changedJoke
            }
        }
    }
}
