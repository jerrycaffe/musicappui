package com.example.musicappui.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.musicappui.ui.navigations.LeftDrawerItem
import com.example.musicappui.ui.navigations.Navigation
import com.example.musicappui.ui.navigations.TopBarNav
import com.example.musicappui.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MainView(modifier: Modifier) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()

    val viewModel: MainViewModel = viewModel()


    //Allow us to find out on which view we currently are
    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentScreen = remember {
        viewModel.currentScreen.value
    }

    val title = remember {
        //change to current screen.title
        mutableStateOf(currentScreen.title)
    }
    var dialogOpen: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }
    @Composable
    fun bottomBar() {
        if (currentScreen is ScreenConfig.DrawerScreen || currentScreen == ScreenConfig.BottomScreen.Home) {
            BottomNavigation(Modifier.wrapContentSize()) {
                //take note of the use of foreach here and not lazy column like drawerbar
                screensInBottom.forEach { item ->
                    BottomNavigationItem(
                        selected = currentRoute == item.bRoute,
                        onClick = { controller.navigate(item.bRoute) },
                        icon = { Icon(contentDescription = item.bTitle, painter = painterResource(id = item.icon)) },
                        label = { Text(text = item.bTitle)},
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Black
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = { TopBarNav(title.value, scope, scaffoldState.drawerState.isOpen) },
        bottomBar = { bottomBar() },

        scaffoldState = scaffoldState,
        drawerContent = {
            LazyColumn(Modifier.padding(16.dp)) {
                items(screensInDrawer) { item ->
                    LeftDrawerItem(selected = currentRoute == item.dRoute, item = item) {
                        scope.launch { scaffoldState.drawerState.close() }
                        if (item.dRoute == "add_account") {
                            dialogOpen.value = true
                        } else {
                            controller.navigate(item.dRoute)
                            title.value = item.dTitle
                        }
                    }
                }
            }
        }

    ) {
        Navigation(navController = controller, viewModel = viewModel, pd = it)
        AccountDialog(dialogOpen = dialogOpen)
    }


}
@Composable
fun AccountDialog(dialogOpen: MutableState<Boolean>){
    println(dialogOpen.value)
    if(dialogOpen.value){
        AlertDialog(
            modifier = Modifier.fillMaxWidth().background(MaterialTheme.colors.primarySurface).padding(8.dp),
            shape = RoundedCornerShape(5.dp),
            backgroundColor = Color.White,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
            onDismissRequest ={
                dialogOpen.value = false
            },
            confirmButton = {
                TextButton(onClick = { dialogOpen.value = false }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = { dialogOpen.value = false }) {
                    Text("Dismiss")
                }
            },
            title = {
                Text(text = "Add Account")
            },
            text = {
                Column(modifier = Modifier
                    .wrapContentHeight()
                    .padding(top = 16.dp),
                    verticalArrangement = Arrangement.Center)
                {
                    TextField(
                        modifier = Modifier.padding(top = 16.dp),
                        label = { Text(text = "Email") },
                        value = "",
                        onValueChange = {})
                    TextField(
                        modifier = Modifier.padding(top = 8.dp),
                        label = { Text(text = "Password") },
                        value = "",
                        onValueChange = {})
                }
            }
        )
    }
}




