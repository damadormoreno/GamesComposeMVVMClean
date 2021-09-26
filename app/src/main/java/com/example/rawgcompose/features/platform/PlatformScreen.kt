package com.example.rawgcompose.features.platform

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PlatformScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Platform Screen", modifier = Modifier.align(Alignment.Center))
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewPlatformScreen(){
    PlatformScreen()
}