package com.ssmmhh.jetpackcomposebasicscodelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
fun MainScreen() {

    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
    } else {
        Greetings()
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Composable
private fun Greetings(names: List<String> = List(1_000) { "#$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
private fun Greeting(name: String) {

    var expanded by rememberSaveable { mutableStateOf(false) }

    val extraPadding by animateDpAsState(
        if (expanded) 56.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        color = MaterialTheme.colors.primary
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Column(
                modifier = Modifier
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp))
                    .weight(10f)
            ) {

                Text(text = "Hello, ")
                Text(
                    text = name,
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
            }
            OutlinedButton(
                modifier = Modifier
                    .padding(16.dp),
                onClick = { expanded = !expanded }) {
                Text(text = if (expanded) "Show less" else "Show more")
            }
        }
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
private fun MainScreenPreview() {
    JetpackComposeBasicsCodelabTheme {
        MainScreen()
    }
}

@Preview(
    showBackground = true, widthDp = 320, uiMode = UI_MODE_NIGHT_YES,
)
@Composable
private fun MainScreenPreviewDark() {
    JetpackComposeBasicsCodelabTheme {
        MainScreen()
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    JetpackComposeBasicsCodelabTheme {
        Greeting("Mahdi")
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun GreetingPreviewDark() {
    JetpackComposeBasicsCodelabTheme {
        Greeting("Mahdi")
    }
}