package com.example.jokesapp.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.jokesapp.models.JokeModel

class ViewModelInMemory:  JokesViewModelInterface, ViewModel()
{
    override var jokesList: MutableList<JokeModel> = mutableStateListOf()

    override var jokeSearchResult: MutableList<JokeModel> = mutableStateListOf()

    init
    {
        if (jokesList.size == 0)
            jokesList.addAll(mutableStateListOf(
                JokeModel(
                    id = 0,
                    question = "What time is it when an elephant sits on your fence?",
                    answer = "Time to get a new fence.",
                    answerIsVisible = true
                ),
                JokeModel(
                    id = 1,
                    question = "What is red and smells like blue paint?",
                    answer = "Red paint.",
                    answerIsVisible = false
                ),
                JokeModel(
                    id = 2,
                    question = "Why did the chicken cross the playground?",
                    answer = "To get to the other slide.",
                    answerIsVisible = true
                ),
                JokeModel(
                    id = 3,
                    question = "What did the mother buffalo say to her child when leaving him at school?",
                    answer = "Bison.",
                    answerIsVisible = false
                )
            ))

    }



    override fun getAllJokes() {
        TODO("Not yet implemented")
    }

    override fun addJoke(joke: JokeModel) {
        jokesList.add(joke.copy(id = jokesList.size +1))
    }

    override fun removeJoke(joke: JokeModel) {
        jokesList.remove(joke)
    }

    override fun findJokesByKeyword(findPhrase: String) {
        if (jokesList.isEmpty()) return  // Prevent searching in an empty list

        // Clear previous search results
        jokeSearchResult.clear()

        // Loop through all jokes in the list and add any that contain the search phrase
        for (joke in jokesList) {
            // Check if the search phrase is in either the question or the answer
            if (joke.question.contains(findPhrase, ignoreCase = true) ||
                joke.answer.contains(findPhrase, ignoreCase = true)
            ) {
                jokeSearchResult.add(joke)
            }
        }
        // If the string is not found, ignore the joke (do not add it to the search results)

    }

    override fun toggleShowJoke(joke: JokeModel) {
        // If there are no search results, then we are not in search mode
        if (jokeSearchResult.size == 0 ) {
            // Set all jokes to answerVisible = false
            jokesList.forEach { it.answerIsVisible = false }

            // Find the position of the joke in the list
            val position = jokesList.indexOf(joke)

            // Replace the joke in the list with a copy where answerVisible = true
            // We must use replacement to trigger the recomposition of the list.
            if (position >= 0) {
                jokesList[position] = jokesList[position].copy(answerIsVisible = true)
            }
        } else {
            // Search results are not empty. We are in search mode
            jokeSearchResult.forEach { it.answerIsVisible = false }

            // Find the position of the joke in the search results
            val position = jokeSearchResult.indexOf(joke)

            // Replace the joke in the list with a copy where answerVisible = true
            if (position >= 0) {
                jokeSearchResult[position] = jokeSearchResult[position].copy(answerIsVisible = true)
            }
        }

    }

}