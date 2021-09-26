package com.example.rawgcompose.features.games.game_search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.rawgcompose.features.games.models.GameSearch
import com.example.rawgcompose.R
import com.example.rawgcompose.core.theme.ui.Gray500

@Composable
fun SearchItem(searchResult: GameSearch, onSearchResultClicked: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .clickable {
                onSearchResultClicked.invoke(searchResult.id)
            }
            .testTag("Search Result Parent")
    ) {
        Row(
            modifier = Modifier
                .padding(bottom = 4.dp)
                .height(40.dp)
                .fillMaxWidth()
        ) {
            Image(
                contentScale = ContentScale.Crop,
                painter = if (searchResult.backgroundImage.isEmpty()) painterResource(id = R.drawable.ic_launcher_foreground)
                else rememberImagePainter(
                    data = searchResult.backgroundImage,
                    builder = {
                        placeholder(R.drawable.ic_launcher_foreground)
                        crossfade(true)
                    }
                ),
                contentDescription = "",
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically)
            )

            Text(
                text = searchResult.name,
                style = MaterialTheme.typography.h5,
                color = Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .align(Alignment.CenterVertically)
            )

        }

        Box(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .height(0.5.dp)
                .fillMaxWidth()
                .background(Gray500)
                .testTag("Search Divider")
        )
    }
}