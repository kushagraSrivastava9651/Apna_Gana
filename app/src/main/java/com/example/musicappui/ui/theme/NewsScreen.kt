import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicappui.R // Replace R with your actual package name

@Composable
fun TitleDescriptionScreen(itemId: Int, title: String, description: String, drawableResId: Int) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h4,
            color = MaterialTheme.colors.primary,
            modifier = Modifier.padding(bottom = 8.dp),
            textAlign = TextAlign.Center
        )

        // Image below title
        val painter: Painter = painterResource(drawableResId)
        Image(
            painter = painter,
            contentDescription = null, // Provide appropriate content description
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 16.dp) // Add padding below the image
        )

        Text(
            text = description,
            style = MaterialTheme.typography.body1,
            color = Color.Gray,
            textAlign = TextAlign.Justify
        )
    }
}

@Preview
@Composable
fun PreviewTitleDescriptionScreen() {
    TitleDescriptionScreen(
        itemId = 1,
        title = "Welcome to My App",
        description = "This is a sample description of the app. It can be longer to demonstrate how text wraps within the composable.",
        drawableResId = R.drawable.image1 // Replace sample_drawable with your actual drawable resource ID
    )
}
