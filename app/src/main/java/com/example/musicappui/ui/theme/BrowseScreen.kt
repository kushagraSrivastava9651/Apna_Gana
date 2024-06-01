package com.example.musicappui.ui.theme


/*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.navigation.NavController
import com.example.musicappui.R



@Composable

fun BrowseView(navController: NavController, modifier: Modifier = Modifier) {
    val recipeViewModel: FetchNewsViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            viewState.loading -> {
                androidx.compose.material.CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }

            viewState.error != null -> {
                androidx.compose.material.Text(text = "Error Occurred: ${viewState.error}")
            }

            else -> {

                LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
                    items(viewState.list) { item ->
                        BrowserItem(
                            item = item,
                             drawable =  item.image,
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

 */
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
 @Composable
fun BrowseView(navController: NavController, modifier: Modifier = Modifier) {
    val recipeViewModel: FetchNewsViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState
     val backgroundColor = MaterialTheme.colors.background

    Box(
        modifier = Modifier.fillMaxSize().background(backgroundColor) ,
        contentAlignment = Alignment.Center
    ) {
        when {
            viewState.loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
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
            }

            viewState.error != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Error Occurred",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.error,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = viewState.error ?: "Unknown Error",
                        style = MaterialTheme.typography.body1
                    )
                    Button(
                        onClick = { /* Handle retry action */ },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "Retry")
                    }
                }
            }

            else -> {

                LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
                    items( viewState.list) { item ->
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


