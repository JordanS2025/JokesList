package com.example.jokesapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jokesapp.models.JokeModel
import com.example.jokesapp.models.ScreenInfo
import com.example.jokesapp.screens.AddJokeScreen
import com.example.jokesapp.screens.LoginScreen
import com.example.jokesapp.screens.SearchScreen
import com.example.jokesapp.screens.ShowJokesScreen
import com.example.jokesapp.ui.theme.CustomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomTheme {
                //LoginScreen()
                //AddJokeScreen()
                //SearchScreen()
                MyApp()

            }
        }
    }
}


@Composable
fun MyApp() {
    var jokes = remember {
        mutableStateListOf(
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
        )
    }

    val navController = rememberNavController()

    // A list of all the screens in the app
    val screens = listOf(
        ScreenInfo(
            routeName = "login",
            title = "Login",
            navIcon = Icons.Default.AccountBox,
            composable = { LoginScreen(navController) }
        ),
        ScreenInfo(
            routeName = "add-item",
            title = "Add Item",
            navIcon = Icons.Default.Add,
            composable = { AddJokeScreen(navController) }
        ),
        ScreenInfo(
            routeName = "show-items",
            title = "Show All Jokes",
            navIcon = Icons.Default.List,
            composable = { ShowJokesScreen(jokes) }
        ),
        ScreenInfo(
            routeName = "search",
            title = "Search",
            navIcon = Icons.Default.Search,
            composable = { SearchScreen(navController) }
        )
    )


    Scaffold(
        bottomBar = { BottomNavBar(navController = navController, screens = screens) },
        modifier = Modifier.padding(bottom = 10.dp)
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            NavigationGraph(navController = navController, screens = screens)
        }
    }

}

private @Composable
fun BottomNavBar(navController: NavHostController, screens: List<ScreenInfo>) {
    // A list of all the screens in the app defined in the "screens" package
    // A variable to keep track of the current screen
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // NavigationBar is a composable that displays a bottom navigation bar.
    NavigationBar {
        // A forEach loop to iterate through all the screens in the app
        screens.forEach { screen ->
            // Navigation Bar Item is a composable used for each item in the bottom navigation bar
            NavigationBarItem(
                label = {
                    Text(text = screen.title)
                },
                icon = {
                    Icon(
                        imageVector = screen.navIcon,
                        contentDescription = "Navigation Icon"
                    )
                },
                selected = currentDestination?.hierarchy?.any { it: NavDestination ->
                    it.route == screen.routeName
                } == true, // If any destination in the navigation hierarchy is this destination, return true

                onClick = {
                    // Navigate to the selected screen
                    navController.navigate(screen.routeName) {
                        // Pop up to the start destination of the graph to
                        popUpTo(navController.graph.findStartDestination().id)
                        // Avoid multiple copies of the same destination when re-selecting the same item
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

@Composable
private fun NavigationGraph(
    navController: NavHostController,
    screens: List<ScreenInfo>
) {
    // A navigation graph is a collection of composable destinations.
    // NavHost is a composable that manages navigation within the app.
    NavHost(
        navController = navController,
        startDestination = screens[0].routeName
    ) {
        screens.forEach { screen ->
            composable(route = screen.routeName) {
                screen.composable()
            }
        }
    }
}


@Composable
fun Joke1(jokeModel: JokeModel, changeVisibility:(id:Int) -> Unit) {
    Text(
        modifier = Modifier.padding(10.dp),
        text = "Joke number: ${jokeModel.id}"
    )

    Text(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                changeVisibility(jokeModel.id)
                Log.d("Joke Tag", "Joke1: $jokeModel")
            },
        color = Color.Blue,
        fontSize = 8.sp,
        textAlign = TextAlign.Center,
        text = jokeModel.question
    )

    if (jokeModel.answerIsVisible) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = jokeModel.answer
        )
    }

    Divider()
}


