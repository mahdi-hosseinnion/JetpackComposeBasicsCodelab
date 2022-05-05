package com.ssmmhh.jetpackcomposebasicscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ssmmhh.jetpackcomposebasicscodelab.ui.theme.JetpackComposeBasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeBasicsCodelabTheme {
                // A surface container using the 'background' color from the theme
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(names: List<String> = listOf("World", "Compose")) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}

@Composable
private fun Greeting(name: String) {
    Surface(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxWidth(),
        color = MaterialTheme.colors.primary
    ) {
        Row(modifier = Modifier) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .weight(10f)
            ) {

                Text(text = "Hello, ")
                Text(text = name)
            }
            OutlinedButton(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(16.dp),
                onClick = { /*TODO*/ }) {
                Text(text = "Show more")
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
private fun MainScreenPreview() {
    MainScreen()
}