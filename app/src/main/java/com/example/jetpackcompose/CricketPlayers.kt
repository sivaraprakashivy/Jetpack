package com.example.jetpackcompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class CricketPlayers : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoadData()
        }
    }

    data class Students(val name: String, val score: Int = 0, val content: String)


    //@Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
//@Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        LoadData()
    }
    @Composable
    fun LoadData()
    {
        JetpackComposeTheme {

            // Sample conversation data
            val conversationSample = listOf(
                Students(
                    "Lexi",
                    content = "Test...Test...Test..."
                ),
                Students(
                    "Lexi",
                    content = """List of Android versions:
            |Android KitKat (API 19)
            |Android Lollipop (API 21)
            |Android Marshmallow (API 23)
            |Android Nougat (API 24)
            |Android Oreo (API 26)
            |Android Pie (API 28)
            |Android 10 (API 29)
            |Android 11 (API 30)
            |Android 12 (API 31)""".trim()
                ),
                Students(
                    "Lexi",
                    content = """I think Kotlin is my favorite programming language.
            |It's so much fun!""".trim()
                ),
                Students(
                    "Lexi",
                    content = "Searching for alternatives to XML layouts..."
                ),
                Students(
                    "Lexi",
                    content = """Hey, take a look at Jetpack Compose, it's great!
            |It's the Android's modern toolkit for building native UI.
            |It simplifies and accelerates UI development on Android.
            |Less code, powerful tools, and intuitive Kotlin APIs :)""".trim()
                ),
                Students(
                    "Lexi",
                    content = "It's available from API 21+ :)"
                ),
                Students(
                    "Lexi",
                    content = "Writing Kotlin for UI seems so natural, Compose where have you been all my life?"
                ),
                Students(
                    "Lexi",
                    content = "Android Studio next version's name is Arctic Fox"
                ),
                Students(
                    "Lexi",
                    content = "Android Studio Arctic Fox tooling for Compose is top notch ^_^"
                ),
                Students(
                    "Lexi",
                    content = "I didn't know you can now run the emulator directly from Android Studio"
                ),
                Students(
                    "Lexi",
                    content = "Compose Previews are great to check quickly how a composable layout looks like"
                ),
                Students(
                    "Lexi",
                    content = "Previews are also interactive after enabling the experimental setting"
                ),
                Students(
                    "Lexi",
                    content = "Have you tried writing build.gradle with KTS?"
                ),
            )

            Conversation(conversationSample.toMutableList())
        }
    }


    @Composable
    fun Conversation(messages: List<Students>) {
        LazyColumn {
            items(messages) { message ->
                MessageCard(message)
            }
        }
    }


    @Composable
    fun MessageCard(msg: Students) {
        Row(modifier = Modifier.padding(all = 5.dp), verticalAlignment = Alignment.Top) {
            Box {
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(0.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                )
                Icon(
                    Icons.Outlined.Home,
                    //  color= MaterialTheme.colorScheme.onBackground,
                    contentDescription = "Check", modifier = Modifier.align(
                        Alignment.BottomEnd
                    )
                )
            }
            Spacer(modifier = Modifier.width(4.dp))

            // We keep track if the message is expanded or not in this
            // variable
            var isExpanded by remember { mutableStateOf(false) }
            // surfaceColor will be updated gradually from one color to the other
            val surfaceColor by animateColorAsState(
                if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            )

            // We toggle the isExpanded variable when we click on this Column
            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = msg.name,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )

                Surface(
                    shape = MaterialTheme.shapes.medium, shadowElevation = 2.dp,
                    color = surfaceColor, modifier = Modifier
                        .animateContentSize()
                        .padding(2.dp)
                ) {
                    Text(
                        text = msg.content.toString(),
                        modifier = Modifier.padding(all = 1.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        // If the message is expanded, we display all its content otherwise we only display the first line
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    )
                }
            }
        }
    }

}