package com.example.musicappui.ui.theme


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text


import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter

import com.example.musicappui.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeView(navController: NavController) {
    val recipeViewModel: FetchNewsViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    val categories = listOf(
        "Live News",
        "New Release",
        "Favorites",
        "Top Rated"
    )

    val shuffledLists = remember {
        mutableStateOf<Map<String, List<Item>>>(emptyMap())
    }

    if (shuffledLists.value.isEmpty() && viewState.list.isNotEmpty()) {
        val shuffledMap = categories.associateWith { category ->
            viewState.list.shuffled().take(6)
        }
        shuffledLists.value = shuffledMap
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            viewState.loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(48.dp)
                        .align(Alignment.Center)
                )
                Text(
                    text = "Loading...",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }

            viewState.error != null -> {
                Text(text = "Error Occurred: ${viewState.error}")
            }

            else -> {
                LazyColumn {
                    categories.forEach { category ->
                        stickyHeader {
                            Text(
                                text = category,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .padding(bottom = 8.dp),
                                style = MaterialTheme.typography.subtitle1
                                    .copy(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 24.sp
                                    )
                            )
                        }
                        item {
                            LazyRow {
                                val itemsForCategory = shuffledLists.value[category] ?: emptyList()
                                items(itemsForCategory) { item ->
                                    BrowserItem(
                                        item = item,
                                        drawable = item.image,
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
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BrowserItem(item: Item, drawable: String, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .size(200.dp)
            .border(3.dp, Color.DarkGray), // Add border to the Card
        onClick = onItemClick,
        elevation = 8.dp, // Add elevation for a lifted effect
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White // Rounded corners for the Card
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp) // Add padding inside the Card
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black // Set title color to black
                ),
                modifier = Modifier.padding(bottom = 8.dp), // Add bottom padding to title text
                maxLines = 2, // Limit title to 2 lines
                overflow = TextOverflow.Ellipsis // Ellipsize text if it exceeds 2 lines
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .size(120.dp) // Set fixed size for the Box
                    .clip(shape = RoundedCornerShape(8.dp)) // Clip the Box with rounded corners
                    .background(Color.LightGray) // Set background color for the Box
            ) {
                val painter = rememberAsyncImagePainter(
                    model = item.image
                )
                Image(
                    painter = painter,
                    contentDescription = item.title,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop // Crop the image inside the Box
                )
                if (painter.state is AsyncImagePainter.State.Error) {
                    // Handle image loading error with an icon or placeholder
                    Icon(
                        painter = painterResource(id = R.drawable.error),
                        contentDescription = "Error",
                        tint = Color.Red, // Set tint color for the icon
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }
    }
}

