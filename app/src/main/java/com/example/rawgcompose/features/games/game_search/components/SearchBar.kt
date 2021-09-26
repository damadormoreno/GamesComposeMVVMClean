package com.example.rawgcompose.features.games.game_search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.rawgcompose.core.extensions.shouldPerformSearch

@Composable
fun SearchBar(
    focusManager: FocusManager,
    onSearch: (String) -> Unit,
    navHostController: NavHostController
) {

    val searchState = remember {
        mutableStateOf(TextFieldValue())
    }

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .testTag("Search Bar"),
            value = searchState.value,
            onValueChange = {
                if (searchState.value.text.trim() != it.text.trim() && it.text.trim()
                        .shouldPerformSearch()
                ) {
                    onSearch(it.text)
                }
                searchState.value = it
            },
            placeholder = {
                Text(
                    modifier = Modifier.background(Color.Transparent),
                    text = "Busca aquí...",
                    color = Black,
                    style = MaterialTheme.typography.body2
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.primaryVariant
            ),
            textStyle = TextStyle(
                color = Black,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            leadingIcon = {
                IconButton(onClick = {
                    focusRequester.freeFocus()
                    navHostController.navigateUp()
                }) {
                    Image(
                        Icons.Filled.ArrowBack,
                        contentDescription = ""
                    )
                }
            },
            trailingIcon = {
                if (searchState.value.text.isNotEmpty()) {
                    IconButton(onClick = {
                        searchState.value = TextFieldValue()
                    }) {
                        Image(
                            Icons.Filled.Close,
                            contentDescription = ""
                        )
                    }
                }
            }
        )

        Box(modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colors.primaryVariant)
            .testTag("Bottom Border")
        )
    }
}
