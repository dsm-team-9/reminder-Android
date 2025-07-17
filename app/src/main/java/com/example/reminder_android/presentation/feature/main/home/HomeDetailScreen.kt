package com.example.reminder_android.presentation.feature.main.home

import android.media.Image
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.reminder_android.R
import com.example.reminder_android.presentation.AppNavigationItem

import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reminder_android.ToggleMajors

import androidx.navigation.NavType
import androidx.navigation.navArgument

@Composable
fun HomeDetailScreen(
    navController: NavController,
    userId: Int,
    homeViewModel: HomeViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        homeViewModel.searchMuseumCard(userId)
    }
    var isGameButtonEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .background(color = Color(0xFFD9D9D9))
            .fillMaxSize()
    ) {
        TopProfile(title = "ghdrlfehd")
        ToggleMajors(
            modifier = Modifier.padding(start = 10.dp),
            onMajorSelected = {}
        )
        Row(
            modifier = Modifier.padding(start = 24.dp, end = 24.dp)
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                text = "홍길동 님의 박물관",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = Bold
            )
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                onClick = { isGameButtonEnabled = !isGameButtonEnabled /* TODO: 게임 시작 로직 */ },
                enabled = isGameButtonEnabled, // 활성화/비활성화 상태를 여기에 연결
                colors = ButtonDefaults.textButtonColors(
                    containerColor = if (isGameButtonEnabled) Color(0xFF1C1F42) else Color.LightGray, // 활성화 시 1C1F42, 비활성화 시 회색
                    contentColor = Color.White // 텍스트 색상 흰색
                ),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(horizontal = 26.dp, vertical = 10.dp)
            ) {
                Text("비활성")
            }
        }
        LazyColumn {
            Log.d("TEST", homeViewModel.cardList.toString())
            items(homeViewModel.cardList) {
                PotteryCard(
                    potteryImage = it.imageUrl,
                    title = it.title,
                    description = it.content,
                    label = it.category.toString(),
                    onDelete = {},
                    onCardClick = {
                        navController.navigate(AppNavigationItem.HomeExhibitsDetail.route)
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
    potteryImage: String,
    title: String,
    description: String,
    label: String = "역사",
    onDelete: () -> Unit,
    onCardClick: () -> Unit,
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
        onClick = onCardClick
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
                AsyncImage(
                    model = potteryImage,
                    contentDescription = "$title 이미지",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                // 체크마크 아이콘 (옵션)
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
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 제

                Spacer(modifier = Modifier.height(6.dp))

                // 설명 텍스트
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    maxLines = 2,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}


