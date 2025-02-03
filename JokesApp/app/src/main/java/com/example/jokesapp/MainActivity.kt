package com.example.jokesapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.jokesapp.ui.theme.CustomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomTheme {
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


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn {
            items(jokes.size) {
                index -> Joke2(jokeModel = jokes[index], context = LocalContext.current
//                {
//                    Log.d("Joke tag", "You clicked joke number: $it")
//
//                    var changeJoke = jokes[it].copy(answerIsVisible =  !jokes[it].answerIsVisible)
//
//                    jokes[it] = changeJoke
//                }
                )
            }
        }
    }
}

private @Composable
fun Joke2(jokeModel: JokeModel, context: Context) {
    Text(
        modifier = Modifier
            .padding(15.dp)
            .clickable {
                Log.d("Jokes tag", "You clicked $jokeModel")
                Toast.makeText(context, jokeModel.answer, Toast.LENGTH_LONG).show()
            },
        text = jokeModel.question
    )

    Divider()
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

data class JokeModel(
    val id: Int,
    val question: String,
    val answer: String,
    val answerIsVisible: Boolean
)
