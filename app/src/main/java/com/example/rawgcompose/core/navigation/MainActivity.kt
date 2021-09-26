package com.example.rawgcompose.core.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import coil.annotation.ExperimentalCoilApi
import com.example.rawgcompose.core.theme.ui.RawgComposeTheme
import com.example.rawgcompose.features.RawgComposeApp
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalCoilApi
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
