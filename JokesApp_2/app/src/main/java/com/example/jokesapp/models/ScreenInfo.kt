package com.example.jokesapp.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

// A data class representing a bottom navigation bar item
data class ScreenInfo(
    val routeName: String, // The route name used to navigate to this screen
    val title: String, // The title to be displayed in the bottom navigation bar
    val navIcon: ImageVector, // The icon for the navigation item
    val composable: @Composable () -> Unit // The screen to display when selected
)
