package com.example.musicappui.ui.theme

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable

import androidx.navigation.NavController
import com.example.musicappui.R


@Composable
fun BrowseView(navController: NavController) {
    val category = listOf(
        Item(id = 1, title = "Breaking News: COVID-19 Vaccine Rollout Begins", description = "Governments worldwide start distributing COVID-19 vaccines to combat the pandemic."),
        Item(id = 2, title = "Tech Giant Unveils Revolutionary AI Technology", description = "A leading technology company introduces groundbreaking AI technology set to reshape industries."),
        Item(id = 3, title = "Climate Change Report: Urgent Action Needed", description = "New scientific findings highlight the critical need for immediate action to address climate change."),
        Item(id = 4, title = "Global Economic Summit Concludes with Trade Agreements", description = "Key global leaders meet to discuss economic strategies and trade agreements."),
        Item(id = 5, title = "Space Exploration: Mission to Mars Successful", description = "Space agency successfully lands a rover on Mars, marking a major milestone in space exploration."),
        Item(id = 6, title = "Political Developments: Historic Peace Treaty Signed", description = "Two countries sign a historic peace treaty after years of conflict."),
        Item(id = 7, title = "Healthcare Breakthrough: New Treatment for Rare Disease", description = "Researchers announce a breakthrough treatment for a rare medical condition."),
        Item(id = 8, title = "Environmental Conservation Efforts Pay Off", description = "Conservationists report positive results from efforts to protect endangered species."),
        Item(id = 9, title = "Artificial Intelligence Innovations Transforming Industries", description = "AI advancements are revolutionizing various sectors, from healthcare to finance."),
        Item(id = 10, title = "Education Reform: New Initiatives for Digital Learning", description = "Educational institutions implement digital learning initiatives to adapt to changing times."),
        Item(id = 11, title = "Sports Update: Record-Breaking Performance in Championship", description = "Athlete achieves a historic milestone in a major sports championship."),
        Item(id = 12, title = "Entertainment Industry Trends: Streaming Services Dominating Market", description = "Streaming platforms continue to gain popularity, reshaping the entertainment landscape.")
    )

    LazyVerticalGrid( GridCells.Fixed(2)) {
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
