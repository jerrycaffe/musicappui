package com.example.musicappui.ui.navigations

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNav(title:String, scope: CoroutineScope, isOpen: Boolean){
    TopAppBar(title = { Text(title) },

        navigationIcon = {
            IconButton(onClick = {
                scope.launch { isOpen }
            }) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Menu")
            }
        }

    )
}