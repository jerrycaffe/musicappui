package com.example.musicappui.viewmodel;

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.musicappui.ui.screens.ScreenConfig

class MainViewModel:ViewModel() {
    private val _currentScreen: MutableState<ScreenConfig> = mutableStateOf(ScreenConfig.DrawerScreen.AddAccount)
    val currentScreen: MutableState<ScreenConfig> = _currentScreen
    fun setCurrentScreen(screen:ScreenConfig){
        _currentScreen.value = screen
    }
}
