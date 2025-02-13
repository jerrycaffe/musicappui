package com.example.musicappui.ui.screens

import androidx.annotation.DrawableRes
import com.example.musicappui.R

sealed class ScreenConfig(val title: String, val route: String) {
    sealed class BottomScreen(
        val bTitle: String, val bRoute: String, @DrawableRes val icon: Int
    ) : ScreenConfig(bTitle, bRoute) {
        object Home : BottomScreen("Home", "home", R.drawable.baseline_music_video_24)
        object Library : BottomScreen("Library", "library", R.drawable.baseline_library_music_24)
        object Browse : BottomScreen(
            "Browse", "browse", R.drawable.baseline_apps_24
        )
    }

    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int) :
        ScreenConfig(dTitle, dRoute) {
        object Account : DrawerScreen(
            "Account",
            "account",
            R.drawable.ic_account
        )

        object Subscription : DrawerScreen(
            "Subscription",
            "subscribe",
            R.drawable.ic_subscribe
        )

        object AddAccount : DrawerScreen(
            "Add Account",
            "add_account",
            R.drawable.baseline_person_add_alt_1_24
        )

    }
}

val screensInDrawer = listOf(
    ScreenConfig.DrawerScreen.Account,
    ScreenConfig.DrawerScreen.Subscription,
    ScreenConfig.DrawerScreen.AddAccount
)

val screensInBottom = listOf(
    ScreenConfig.BottomScreen.Home,
    ScreenConfig.BottomScreen.Browse,
    ScreenConfig.BottomScreen.Library
)