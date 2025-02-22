package com.example.jokesapp.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.navigation.NavHostController

// Opt-in is used for experimental Material3 API features. May not be necessary in the future.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    // Create data entry form for username and password
    // Create a button to submit the form

    // Username and password are variables to hold the data entered by the user
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Center the content
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inversePrimary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = "Login",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Username field
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.background(Color.White, shape = RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password field
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.background(Color.White, shape = RoundedCornerShape(10.dp))
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Login button
        Button(onClick = { navController.navigate("search item") }) {
            Text(text = "Login")
        }
    }
}
