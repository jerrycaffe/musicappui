package com.example.musicappui.ui.navigations

import android.graphics.drawable.Drawable
import androidx.compose.material.DrawerState
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
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
fun TopBarNav(
    title: String,
    scope: CoroutineScope,
    modalSheetState: ModalBottomSheetState,
    scaffoldState: ScaffoldState
) {
    TopAppBar(title = { Text(title) },
        actions = {
            IconButton(onClick = {
                scope.launch { if (modalSheetState.isVisible) modalSheetState.hide() else modalSheetState.show() }
            }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }
        },
        navigationIcon = {
            IconButton(onClick = { scope.launch {
                scaffoldState.drawerState.open()
            } }) {
                Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Menu")
            }
        }

    )
}