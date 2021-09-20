package com.example.rawgcompose.features.common_components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun HomeAppBar(title: String, searchClick: () -> Unit,
               filterClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
        backgroundColor = MaterialTheme.colors.primaryVariant,
        actions = {
            IconButton(onClick = searchClick) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
            }

            IconButton(onClick = filterClick) {
                Icon(
                    imageVector = Icons.Filled.FilterList,
                    contentDescription = "Filter",
                    tint = Color.White
                )
            }
        }
    )
}