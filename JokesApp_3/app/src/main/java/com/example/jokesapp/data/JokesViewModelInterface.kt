package com.example.jokesapp.data

import com.example.jokesapp.models.JokeModel

interface JokesViewModelInterface
{
    // The UI will use these two lists to display the jokes
// `jokesList` is the master list of all jokes
// `jokeSearchResult` is the list of jokes that match the search criteria
    var jokesList: MutableList<JokeModel>
    var jokeSearchResult: MutableList<JokeModel>

// These functions will be implemented in the ViewModel
// The UI will call these functions to interact with the ViewModel
// The ViewModel will then interact with the database or in-memory data
// The ViewModel will update the UI by modifying `jokesList` and `jokeSearchResult`

// The UI will observe `jokesList` and `jokeSearchResult`
// When these lists are updated, the UI will automatically update itself
// The UI will not need to know whether the ViewModel is using a database or in-memory data

    /**
     * Retrieves all jokes from the database or in-memory data
     */
    fun getAllJokes()

    /**
     * Adds a joke to the database or in-memory data
     * @param joke The joke to be added
     */
    fun addJoke(joke: JokeModel)

    /**
     * Removes a joke from the database or in-memory data
     * @param joke The joke to be removed
     */
    fun removeJoke(joke: JokeModel)

    /**
     * Finds jokes that match the given search criteria
     * @param findPhrase The phrase to search for in the joke's question and answer
     * Updates `jokeSearchResult` with the jokes that match the search criteria
     */
    fun findJokesByKeyword(findPhrase: String)

    /**
     * Toggles the visibility of the joke's answer
     * Updates `jokesList` or `jokeSearchResult` with the modified joke
     * @param joke The joke whose visibility should be toggled
     */
    fun toggleShowJoke(joke: JokeModel)

}