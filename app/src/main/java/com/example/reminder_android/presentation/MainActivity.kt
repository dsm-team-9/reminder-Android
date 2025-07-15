package com.example.reminder_android.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.reminder_android.BottomMenu
import com.example.reminder_android.presentation.feature.signin.SignInScreen
import com.example.reminder_android.presentation.feature.signup.SignUpScreen
import com.example.reminder_android.presentation.theme.ReminderAndroidTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReminderAndroidTheme {
                BaseApp()
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomMenu.MY,
        BottomMenu.SOCIAL,
        BottomMenu.CHAT,
        BottomMenu.HOME,
    )

    BottomAppBar (
        containerColor = Color.White,
        contentColor = Color(0xFF3F414E),
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = stringResource(id = item.title)) },
                label = { Text(text = stringResource(id = item.title), fontSize = 9.sp) },
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF5F6074),
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color(0xFF3F414E),
                    unselectedTextColor = Color(0xFF3F414E),
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}


@Composable
private fun BaseApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppNavigationItem.SignIn.route) {
        composable(AppNavigationItem.SignIn.route) {
            SignInScreen(navController = navController)
        }
        composable(AppNavigationItem.SignUp1.route) {
            SignUpScreen(navController = navController)
        }
        composable(BottomMenu.MY.route) {
            val mainAppNavController = rememberNavController()
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    BottomAppBar {  }
                    BottomNavigationBar(navController = mainAppNavController)
                },
                floatingActionButton = {
                    FloatingActionButton(onClick = { /* TODO: Handle FAB click */ }) {
                        Icon(Icons.Filled.Add, "Add") // Using Material Icons Add icon
                    }
                },
                floatingActionButtonPosition = FabPosition.Center,
            ) { paddingValues ->
                NavHost(navController = mainAppNavController, startDestination = BottomMenu.HOME.route) {
                    composable(BottomMenu.MY.route) {
                        Box(modifier = Modifier.padding(paddingValues)) {
                            TestScreen(navController = mainAppNavController, screenName = stringResource(id = BottomMenu.MY.title))
                        }
                    }
                    composable(BottomMenu.SOCIAL.route) {
                        Box(modifier = Modifier.padding(paddingValues)) {
                            TestScreen(navController = mainAppNavController, screenName = stringResource(id = BottomMenu.SOCIAL.title))
                        }
                    }
                    composable(BottomMenu.HOME.route) {
                        Box(modifier = Modifier.padding(paddingValues)) {
                            TestScreen(navController = mainAppNavController, screenName = stringResource(id = BottomMenu.HOME.title))
                        }
                    }
                    composable(BottomMenu.CHAT.route) {
                        Box(modifier = Modifier.padding(paddingValues)) {
                            TestScreen(navController = mainAppNavController, screenName = stringResource(id = BottomMenu.CHAT.title))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TestScreen(
    navController: NavController,
    screenName: String,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Text(text = screenName)
    }
}