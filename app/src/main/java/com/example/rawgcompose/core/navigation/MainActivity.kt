package com.example.rawgcompose.core.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import coil.annotation.ExperimentalCoilApi
import com.example.rawgcompose.core.theme.RawgComposeTheme
import com.example.rawgcompose.features.RawgComposeApp
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RawgComposeTheme {
                RawgComposeApp()
            }
        }
    }
}
