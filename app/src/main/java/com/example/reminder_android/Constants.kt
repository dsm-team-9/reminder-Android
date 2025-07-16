package com.example.reminder_android

object Constants {
    const val NAVIGATION_MY = "MY"
    const val NAVIGATION_SOCIAL = "social"
    const val NAVIGATION_HOME = "home"
    const val NAVIGATION_CHAT = "chat"
}

enum class MajorTag(val displayName: String) {
    HISTORY("역사"),
    ART("미술"),
    SCIENCE("과학"),
    LITERATURE("문학"),
    MUSIC("음악"),
    SPORTS("체육"),
    ETC("기타"),
}