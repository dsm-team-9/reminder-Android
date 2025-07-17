package com.example.reminder_android.presentation

sealed class AppNavigationItem(val route: String) {
    data object SignIn : AppNavigationItem("signIn")

    data object SignUp : AppNavigationItem("signUp")

    data object HomeDetail : AppNavigationItem("HomeDetail")

<<<<<<< HEAD
    data object HomeExhibitsDetail : AppNavigationItem("HomeExhibitsDetail")

    data object ChatAIDetail : AppNavigationItem("ChatAIDetail")

    data object UpLoadExhibit : AppNavigationItem("UpLoadExhibit")

    data object MyDetail : AppNavigationItem("MyDetail")

    data object Main : AppNavigationItem("Main")
=======

>>>>>>> origin/main
}