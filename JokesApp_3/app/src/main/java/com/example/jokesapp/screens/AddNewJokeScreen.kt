package com.example.jokesapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.navigation.NavHostController
import com.example.jokesapp.data.JokesViewModelInterface
import com.example.jokesapp.models.JokeModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddJokeScreen(navController: NavHostController, viewModel: JokesViewModelInterface) {
    // Variables for each field in the form
    var jokeQuestion by remember { mutableStateOf("") }
    var jokeAnswer by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inversePrimary) // Background color
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "Add a Joke",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Joke Question Field
            TextField(
                value = jokeQuestion,
                onValueChange = { jokeQuestion = it },
                label = { Text("Joke Question") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Joke Answer Field
            TextField(
                value = jokeAnswer,
                onValueChange = { jokeAnswer = it },
                label = { Text("Joke Answer") },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Submit Button
            Button(onClick = {
                val newJoke = JokeModel(
                    id = 0,
                    question = jokeQuestion,
                    answer = jokeAnswer,
                    answerIsVisible = false
                )
                viewModel.addJoke(newJoke)
                navController.navigate("show-items")
            }) {
                Text(text = "Add Joke")
            }

        }
    }
}
