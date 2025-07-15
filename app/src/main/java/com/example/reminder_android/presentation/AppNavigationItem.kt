package com.example.reminder_android.presentation

sealed class AppNavigationItem(val route: String) {
    data object SignIn : AppNavigationItem("signIn")

    data object SignUp1 : AppNavigationItem("signUp1")

    data object HomeDetail : AppNavigationItem("HomeDetail")


}