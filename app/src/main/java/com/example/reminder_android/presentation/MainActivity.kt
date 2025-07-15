package com.example.reminder_android.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.reminder_android.presentation.theme.ReminderAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReminderAndroidTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(navController = navController) }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        Navigation(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomMenu.Home.route) {
        composable(BottomMenu.Home.route) {
            TestScreen(navController = navController, screenName = stringResource(id = BottomMenu.Home.title))
        }
        composable(BottomMenu.Recruitments.route) {
            TestScreen(navController = navController, screenName = stringResource(id = BottomMenu.Recruitments.title))
        }
        composable(BottomMenu.Bookmark.route) {
            TestScreen(navController = navController, screenName = stringResource(id = BottomMenu.Bookmark.title))
        }
        composable(BottomMenu.MyPage.route) {
            TestScreen(navController = navController, screenName = stringResource(id = BottomMenu.MyPage.title))
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomMenu.Home,
        BottomMenu.Recruitments,
        BottomMenu.Bookmark,
        BottomMenu.MyPage,
    )

    NavigationBar (
        containerColor = Color.White,
        contentColor = Color(0xFF3F414E)
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
                }
            )
        }
    }
}

@Composable
fun TestScreen(
    navController: NavController,
    screenName: String,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = screenName)
    }
}