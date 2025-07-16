package com.example.reminder_android

import androidx.compose.ui.graphics.Color

object Constants {
    const val NAVIGATION_MY = "MY"
    const val NAVIGATION_SOCIAL = "social"
    const val NAVIGATION_HOME = "home"
    const val NAVIGATION_CHAT = "chat"
}

enum class MajorTag(val displayName: String, val color: Color) {
    HISTORY("역사", Color(0xFFF9F6DC)), // 예시 색상
    ART("미술", Color(0xFFE0F7FA)),
    SCIENCE("과학", Color(0xFFFFFDE7)),
    LITERATURE("문학", Color(0xFFF3E5F5)),
    MUSIC("음악", Color(0xFFE8F5E9)),
    SPORTS("체육", Color(0xFFFBE9E7)),
    ETC("기타", Color(0xFFECEFF1)),
}