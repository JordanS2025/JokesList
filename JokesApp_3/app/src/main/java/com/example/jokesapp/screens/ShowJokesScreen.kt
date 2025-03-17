package com.example.jokesapp.screens

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.example.jokesapp.Joke1
import com.example.jokesapp.data.JokesViewModelInterface
import com.example.jokesapp.models.JokeModel


public @Composable
fun ShowJokesScreen(viewModel: JokesViewModelInterface) {

    var jokes: MutableList<JokeModel>

    // If the search result is empty, show all jokes
     if (viewModel.jokeSearchResult.size  == 0) {
        jokes = viewModel.jokesList
    } else {
        // Otherwise, show the search result
        jokes = viewModel.jokeSearchResult
    }

    LazyColumn {
        items(jokes.size) { index ->
            Joke1(jokeModel = jokes[index]) { it ->

                Log.d( "ShowJokesScreen", "Joke clicked: ${jokes[index]}")
                Log.d( "ShowJokesScreen",  "Index number clicked: $index")

                viewModel.toggleShowJoke(jokes[index])
            }
        }
    }


}
