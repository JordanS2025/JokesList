package com.example.jokesapp.models


data class JokeModel(
    val id: Int,
    val question: String,
    val answer: String,
    val answerIsVisible: Boolean
)