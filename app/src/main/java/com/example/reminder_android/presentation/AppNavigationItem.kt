package com.example.reminder_android.presentation

sealed class AppNavigationItem(val route: String) {
    data object SignIn : AppNavigationItem("signIn")

    data object SignUp1 : AppNavigationItem("signUp1")

    data object HomeDetail : AppNavigationItem("HomeDetail")

    data object SignUp3 : AppNavigationItem("signUp3")

    data object SignUp4 : AppNavigationItem("signUp4")

    data object MainApp : AppNavigationItem("mainApp")
}