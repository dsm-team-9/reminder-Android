package com.example.reminder_android.presentation

import android.appwidget.AppWidgetProvider
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.reminder_android.BottomMenu
import com.example.reminder_android.presentation.feature.main.chat.ChatAIDetailScreen
import com.example.reminder_android.presentation.feature.main.chat.ChatScreen
import com.example.reminder_android.presentation.feature.main.home.HomeDetailScreen
import com.example.reminder_android.presentation.feature.main.home.HomeExhibitDetailScreen
import com.example.reminder_android.presentation.feature.main.home.HomeScreen
import com.example.reminder_android.presentation.feature.main.my.MyDetailScreen
import com.example.reminder_android.presentation.feature.main.my.MyScreen
import com.example.reminder_android.presentation.feature.main.social.SocialScreen
import com.example.reminder_android.presentation.feature.main.upLoadExhibit.UpLoadExhibit
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
        BottomMenu.HOME,
        BottomMenu.CHAT,
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
        composable(AppNavigationItem.SignUp.route) {
            SignUpScreen(navController = navController)
        }
        composable(AppNavigationItem.Main.route) {
            val mainAppNavController = rememberNavController()
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    BottomAppBar {  }
                    BottomNavigationBar(navController = mainAppNavController)
                },
                floatingActionButton = {
                    FloatingActionButton(
                        modifier = Modifier.offset(y = 56.dp),
                        onClick = { mainAppNavController.navigate(AppNavigationItem.UpLoadExhibit.route) },
                        shape = CircleShape,
                    ) {
                        Icon(Icons.Filled.Add, "Add") // Using Material Icons Add icon
                    }
                },
                floatingActionButtonPosition = FabPosition.Center,
            ) { paddingValues ->
                NavHost(navController = mainAppNavController, startDestination = BottomMenu.HOME.route) {
                    composable(BottomMenu.MY.route) {
                        Box(modifier = Modifier.padding(paddingValues)) {
                            MyScreen(navController = mainAppNavController)
                        }
                    }
                    composable(BottomMenu.SOCIAL.route) {
                        Box(modifier = Modifier.padding(paddingValues)) {
                            SocialScreen(navController = mainAppNavController)
                        }
                    }
                    composable(BottomMenu.HOME.route) {
                        Box(modifier = Modifier.padding(paddingValues)) {
                            HomeScreen(navController = mainAppNavController)
                        }
                    }
                    composable(BottomMenu.CHAT.route) {
                        Box(modifier = Modifier.padding(paddingValues)) {
                            ChatScreen(navController = mainAppNavController)
                        }
                    }
                    composable(AppNavigationItem.HomeDetail.route) {
                        HomeDetailScreen(navController = mainAppNavController)
                    }
                    composable(AppNavigationItem.HomeExhibitsDetail.route) {
                        HomeExhibitDetailScreen(navController = mainAppNavController)
                    }
                    composable(AppNavigationItem.MyDetail.route) {
                        MyDetailScreen(navController = mainAppNavController)
                    }
                    composable(AppNavigationItem.ChatAIDetail.route) {
                        ChatAIDetailScreen(navController = mainAppNavController)
                    }
                    composable(AppNavigationItem.UpLoadExhibit.route) {
                        UpLoadExhibit(navController = mainAppNavController)

                    }
                }
            }
        }
    }
}