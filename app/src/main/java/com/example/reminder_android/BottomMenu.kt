package com.example.reminder_android

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.reminder_android.Constants.NAVIGATION_CHAT
import com.example.reminder_android.Constants.NAVIGATION_HOME
import com.example.reminder_android.Constants.NAVIGATION_MY
import com.example.reminder_android.Constants.NAVIGATION_SOCIAL

sealed class BottomMenu(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
) {
    data object MY : BottomMenu(
        route = NAVIGATION_MY,
        icon = R.drawable.ic_museum,
        title = R.string.my,
    )

    data object SOCIAL : BottomMenu(
        route = NAVIGATION_SOCIAL,
        icon = R.drawable.ic_people,
        title = R.string.social,
    )

    data object HOME : BottomMenu(
        route = NAVIGATION_HOME,
        icon = R.drawable.ic_person,
        title = R.string.home,
    )

    data object CHAT : BottomMenu(
        route = NAVIGATION_CHAT,
        icon = R.drawable.ic_chat,
        title = R.string.chat,
    )
}