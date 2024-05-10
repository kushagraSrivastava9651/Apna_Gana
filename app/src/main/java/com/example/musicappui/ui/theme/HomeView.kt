package com.example.musicappui.ui.theme


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text


import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musicappui.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeView(navController: NavController) {







    val grouped = listOf<String>("Live News","New Release","Favorites","Top  Rated").groupBy { it[0] }
  //  val grouped = items.groupBy { it.title.first().toUpperCase().toString() }

    LazyColumn {
        grouped.forEach {
            stickyHeader {
                Text(text = it.value[0], modifier = Modifier.padding(16.dp))
                LazyRow {
                    items(category) { item ->
                        BrowserItem(
                            item = item,
                            drawable = R.drawable.baseline_apps_24,
                            onItemClick = {
                                navController.navigate("details/${item.id}")
                            }
                        )
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BrowserItem(item: Item, drawable: Int, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .size(200.dp)
            .border(3.dp, Color.DarkGray), // Add border to the Card
        onClick = onItemClick
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .width(150.dp) // Set fixed width for the Box
                    .height(60.dp) // Set fixed height for the Box
            ) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.h6, // Use h6 typography for title
                    color = Color.Black, // Set title color to black
                    modifier = Modifier.padding(8.dp) // Add padding to title text
                )
            }
            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(id = drawable),
                contentDescription = item.title,
                modifier = Modifier.size(100.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreviewBrowserItem() {
    BrowserItem(
        item = Item(id = 1, title = "Sample Item", description = "This is a sample item"),
        drawable = R.drawable.baseline_apps_24,
        onItemClick = {}
    )
}
