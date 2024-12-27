package com.example.musicappui.ui.navigations

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.musicappui.Library
import com.example.musicappui.ui.screens.ScreenConfig
import com.example.musicappui.view.AccountView
import com.example.musicappui.view.Browse
import com.example.musicappui.view.Home
import com.example.musicappui.view.Subscription
import com.example.musicappui.viewmodel.MainViewModel

@Composable
fun Navigation(navController: NavController, viewModel: MainViewModel, pd: PaddingValues) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = ScreenConfig.DrawerScreen.Account.route, modifier = Modifier.padding(pd)
    ) {
        composable(ScreenConfig.BottomScreen.Home.route) {
           Home()
        }
        composable(ScreenConfig.BottomScreen.Library.route) {
Library()        }
        composable(ScreenConfig.BottomScreen.Browse.route) {
           Browse()
        }
        composable(ScreenConfig.DrawerScreen.Account.route) { AccountView() }
        composable(ScreenConfig.DrawerScreen.Subscription.route) { Subscription() }
    }
}