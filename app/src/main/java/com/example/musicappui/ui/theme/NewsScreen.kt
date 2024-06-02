/*
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
import coil.compose.rememberAsyncImagePainter
import com.example.musicappui.R // Replace R with your actual package name

@Composable
fun TitleDescriptionScreen(itemId: Int, title: String, description: String, drawableResId: String) {
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
        val painter = rememberAsyncImagePainter(
            model = drawableResId
        )

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


 */
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.musicappui.R

@Composable
fun TitleDescriptionScreen(itemId: Int, title: String, description: String, drawableResId: String) {
    val backgroundColor = MaterialTheme.colors.background
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(backgroundColor),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Title text
                Text(
                    text = title,
                    style = MaterialTheme.typography.h4,
                    color = Color.Magenta,
                    modifier = Modifier.padding(bottom = 8.dp),
                    textAlign = TextAlign.Center
                )

                // Image below title
                val painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(context)
                        .data(data = drawableResId)
                        .apply {
                            placeholder(R.drawable.loading) // Placeholder image while loading
                            error(R.drawable.error) // Image to show on error
                        }
                        .build()
                )

                Image(
                    painter = painter,
                    contentDescription = null, // Provide appropriate content description
                    modifier = Modifier
                        .size(240.dp)
                        .padding(bottom = 16.dp) // Add padding below the image
                )

                // Description text
                Text(
                    text = description,
                    style = MaterialTheme.typography.body1,
                    color = Color.Black,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.widthIn(max = 400.dp) // Limit the width for better readability
                )
            }
        }

        item {
            // Brand logo and share button at the bottom
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Brand logo
                Image(
                    painter = painterResource(id = R.drawable.quick_news_logo),
                    contentDescription = null, // Provide appropriate content description
                    modifier = Modifier
                        .size(64.dp)
                )
                // Share button
                IconButton(
                    onClick = {
                        // Share functionality
                        shareContent(context = context, title = title, description = description)
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_share_24), // Replace with your share icon resource ID
                        contentDescription = "Share",
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
        }
    }
}

fun shareContent(context: Context, title: String, description: String) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, "$title\n\n$description")
        type = "text/plain"
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share via"))
}
