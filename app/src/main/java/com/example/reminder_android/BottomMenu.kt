package com.example.reminder_android

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.reminder_android.Constants.NAVIGATION_BOOKMARK
import com.example.reminder_android.Constants.NAVIGATION_HOME
import com.example.reminder_android.Constants.NAVIGATION_MY_PAGE
import com.example.reminder_android.Constants.NAVIGATION_RECRUITMENTS

sealed class BottomMenu(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val title: Int,
) {
    data object Home : BottomMenu(
        route = NAVIGATION_HOME,
        icon = R.drawable.ic_home,
        title = R.string.home,
    )

    data object Recruitments : BottomMenu(
        route = NAVIGATION_RECRUITMENTS,
        icon = R.drawable.ic_recruitment,
        title = R.string.recruitment,
    )

    data object Bookmark : BottomMenu(
        route = NAVIGATION_BOOKMARK,
        icon = R.drawable.ic_bookmark,
        title = R.string.bookmark,
    )

    data object MyPage : BottomMenu(
        route = NAVIGATION_MY_PAGE,
        icon = R.drawable.ic_my_page,
        title = R.string.my_page,
    )
}