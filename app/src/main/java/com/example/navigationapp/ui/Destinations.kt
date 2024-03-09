package com.example.navigationapp.ui

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Destinations {
    val route: String
}

object Screen1: Destinations {
    override val route = "screen1"
}

object Screen2: Destinations {
    override val route = "screen2"
}

object Screen3: Destinations {
    override val route = "screen3"
}

object ScreenWithArg : Destinations {
    override val route = "/{a}"

    val arguments = listOf(
        navArgument("a") { type = NavType.StringType }
    )
}

// Screens to be displayed in the top RallyTabRow
val Screens = listOf(Screen1, Screen2, Screen3, ScreenWithArg)