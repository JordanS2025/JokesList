package com.example.jokesapp.models


data class JokeModel(
    val id: Int = 0,
    val question: String = "",
    val answer: String = "",
    var answerIsVisible: Boolean = false
)