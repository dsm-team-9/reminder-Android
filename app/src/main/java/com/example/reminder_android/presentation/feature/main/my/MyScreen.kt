package com.example.reminder_android.presentation.feature.main.my

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import coil.compose.AsyncImage
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
fun MyScreen(
    navController: NavController,
    myViewModel: MyViewModel = viewModel()
) {
    var isSelected by remember { mutableStateOf(false) } // 예시: 활성화/비활성화 상태
    var isGameButtonEnabled by remember { mutableStateOf(true) } // 게임 시작 버튼 활성화/비활성화 상태
    var showPotteryCard by remember { mutableStateOf(true) }

    var checkedStates by remember { mutableStateOf(emptyMap<Int, Boolean>()) }

    LaunchedEffect(myViewModel.myCardList) {
        // myCardList가 변경될 때마다 checkedStates 초기화
        myViewModel.fetchMyCard()
        checkedStates = myViewModel.myCardList.associateBy({ it.id }, { false })
    }
    Log.d("TEST", myViewModel.myCardList.toString())
    Column(
        modifier = Modifier
            .background(color = Color(0xFFF2F2F2))
            .fillMaxSize()
    ) {
        TopProfile(
            title = "홍길동"
        )
        ToggleMajors(
            modifier = Modifier.padding(start = 10.dp)
        ) {  }
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
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Text("비활성")
            }
        }
        if (showPotteryCard) {
            LazyColumn {
                items(myViewModel.myCardList) { card ->
                    PotteryCard(
                        potteryImage = card.imageUrl,
                        title = card.title,
                        description = card.content,
                        label = card.category.toString(), // 실제 라벨 값으로 변경
                        onCardClick = { navController.navigate(AppNavigationItem.MyDetail.route) },
                        onDelete = { showPotteryCard = false },
                        isChecked = checkedStates[card.id] ?: false, // 개별 체크 상태 사용
                        onCheckedChange = {
//                            isChecked ->
//                            checkedStates = checkedStates.toMutableMap().apply {
//                                this[card.id] = isChecked
//                            }
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun PotteryCard(
    potteryImage: String?,
    title: String,
    description: String,
    label: String? = "역사",
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
                Icon(
                    imageVector = if (isChecked) Icons.Default.CheckCircle else Icons.Default.CheckCircleOutline,
                    contentDescription = "선택됨 표시",
                    tint = if (isChecked) Color(0xFF393e46) else Color.LightGray,
                    modifier = Modifier
                        .size(32.dp)
                        .align(Alignment.TopStart)
                        .offset(8.dp, 8.dp)
                        .clickable { onCheckedChange() }
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
                            text = label ?: "",
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
                    Row {
                        IconButton(onClick = onDelete) {
                            Icon(
                                modifier = Modifier
                                    .align(Alignment.Top)
                                    .size(20.dp),
                                imageVector = Icons.Default.Delete,
                                contentDescription = "삭제"
                            )
                        }
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
