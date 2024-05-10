package com.example.musicappui.ui.theme
/*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable

fun TitleDescriptionScreen(itemId: Int, title: String, description: String) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h4
        )
        Text(
            text = description,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview
@Composable
fun PreviewTitleDescriptionScreen() {
    TitleDescriptionScreen(
        itemId = 1,
        title = "Welcome to My App",
        description = "This is a sample description of the app."
    )
}

 */
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TitleDescriptionScreen(itemId: Int, title: String, description: String) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primary, // Change color to primary color
            modifier = Modifier.padding(bottom = 8.dp),
            textAlign = TextAlign.Center // Center align text
        )
        Text(
            text = description,
            style = MaterialTheme.typography.body1,
            color = Color.Gray, // Change color to gray
            textAlign = TextAlign.Justify // Justify align text
        )
    }
}

@Preview
@Composable
fun PreviewTitleDescriptionScreen() {
    TitleDescriptionScreen(
        itemId = 1,
        title = "Welcome to My App",
        description = "This is a sample description of the app. It can be longer to demonstrate how text wraps within the composable."
    )
}
