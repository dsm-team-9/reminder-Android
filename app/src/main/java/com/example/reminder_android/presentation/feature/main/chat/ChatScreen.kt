package com.example.reminder_android.presentation.feature.main.chat

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.reminder_android.R
import com.example.reminder_android.ToggleMajors
import com.example.reminder_android.data.response.MyCardResponse
import com.example.reminder_android.presentation.AppNavigationItem
import com.example.reminder_android.presentation.feature.main.home.TopProfile
import com.example.reminder_android.presentations.data.api.ApiProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(
    navController: NavController,
    chatViewModel: ChatViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        chatViewModel.fetchMyCard()
    }

    Column(
        modifier = Modifier
            .background(color = Color(0xFFD9D9D9))
            .fillMaxSize()
    ) {
        TopProfile(
            title = "",
        )
        ToggleMajors(
            modifier = Modifier.padding(start = 10.dp)
        ) {  }
        LazyColumn {
            items(chatViewModel.chatCardList) {
                PotteryCard(
                    potteryImage = painterResource(R.drawable.testimg),
                    title = "qltxkfansmlxhrl",
                    description = "",
                    label = "역사",
                    cardId = it.id, // cardId 전달
                    onDelete = {},
                    onChatClick = {
                        navController.navigate(AppNavigationItem.ChatAIDetail.route + "/${it.id}") // cardId 전달
                    },
                    isChecked = true,
                    onCheckedChange = {},
                )
            }
        }
    }
}

@Composable
private fun PotteryCard(
    potteryImage: Painter,
    title: String,
    description: String,
    label: String = "역사",
    cardId: Int, // cardId 파라미터 추가
    onDelete: () -> Unit,
    onChatClick: () -> Unit,
    isChecked: Boolean,
    onCheckedChange: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(),
        modifier = Modifier
            .padding(12.dp) // Revert padding to 12.dp as in MuseumItem's parent
            .fillMaxWidth()
            .height(140.dp), // Set height to 120.dp as in MuseumItem
    ) {
        Row(
            modifier = Modifier.padding(top = 4.dp, start = 13.dp, end = 8.dp),
            verticalAlignment = Alignment.Top
        ) {
            // 이미지 섹션
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight() // Set to fillMaxHeight as in MuseumItem
            ) {
                Image(
                    painter = potteryImage,
                    contentDescription = "$title 이미지",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(16.dp))

            // 텍스트 및 액션 섹션
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    // 라벨 칩
                    Column(
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Text(
                            text = label,
                            fontSize = 12.sp,
                            color = Color(0xFF888800),
                            modifier = Modifier
                                .background(Color(0xFFF9F6DC), shape = RoundedCornerShape(10.dp))
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        )
                        Text(
                            text = title,
                            fontSize = 15.sp,
                            fontWeight = Bold,
                            color = Color.Black
                        )
                    }

                    // 수정/삭제 버튼
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 제

                Spacer(modifier = Modifier.height(6.dp))

                // 설명 텍스트
                TextButton(
                    onClick = onChatClick
                ) {
                    Text(
                        text = "채팅하기",
                        fontSize = 14.sp,
                        color = Color.DarkGray,
                        maxLines = 2,
                        textAlign = TextAlign.Right
                    )
                }
            }
        }
    }
}