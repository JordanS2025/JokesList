package com.example.jokesapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jokesapp.data.JokesViewModelInterface

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavHostController, viewModel: JokesViewModelInterface) {
    // Variable for the search field
    var searchString by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary), // Background color
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Search for a Joke",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Search TextField
        TextField(
            value = searchString,
            onValueChange = { searchString = it },
            label = { Text("Joke Question") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Search Button
        Button(onClick = {
            viewModel.findJokesByKeyword(searchString)
            if (viewModel.jokeSearchResult.isNotEmpty()) {
                navController.navigate("show-items")
            }
        }) {
            Text(text = "Search")
        }


    }
}
