package com.example.rawgcompose.core.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.rawgcompose.R

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector?) {
    object HomeScreen : Screen("home_screen", R.string.home_screen, Icons.Filled.PlayArrow)
    object GameDetailScreen : Screen("game_detail_screen", R.string.game_detail_screen, null)
    object PlatformScreen : Screen("platform_screen", R.string.platform_screen, Icons.Filled.List)
}
