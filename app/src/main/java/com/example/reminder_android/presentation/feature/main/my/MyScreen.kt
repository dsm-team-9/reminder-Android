package com.example.reminder_android.presentation.feature.main.my

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.reminder_android.R

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import com.example.reminder_android.ToggleMajors
import com.example.reminder_android.presentation.AppNavigationItem
import com.example.reminder_android.presentation.feature.main.home.TopProfile

import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import java.time.format.TextStyle

@Composable
fun MyScreen(
    navController: NavController,
) {
    var isSelected by remember { mutableStateOf(false) } // 예시: 활성화/비활성화 상태

    Column(
        modifier = Modifier
            .background(color = Color(0xFFF2F2F2))
            .fillMaxSize()
    ) {
        TopProfile(
            title = "홍길동"
        )
        ToggleMajors {  }
        Row {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 20.dp),
                text = "홍길동 님의 박물관",
                fontSize = 20.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            TextButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                onClick = { /* TODO: 게임 시작 로직 */ },
                enabled = true, // 활성화/비활성화 상태를 여기에 연결
                colors = ButtonDefaults.textButtonColors(
                    containerColor = if (true) Color(0xFF1C1F42) else Color.LightGray, // 활성화 시 1C1F42, 비활성화 시 회색
                    contentColor = Color.White // 텍스트 색상 흰색
                )
            ) {
                Text("게임시작")
            }
        }
        PotteryCard(
            potteryImage = painterResource(R.drawable.testimg),
            title = "빗살무늬토기",
            description = "stringlabelstringlabelstringlabelstringlabelstringlabelstringlabelstringlabelstringlabelstringlabelstringlabelstringlabelstringlabelstringlabelstringlabelstringlabelstringlabelstringlabelstringlabelstringlabel",
            label = "역사", // 실제 라벨 값으로 변경
            selected = isSelected, // 상태 전달
            onCheckToggle = { isSelected = !isSelected }, // 클릭 시 상태 변경
            onDelete = {},
            onCardClick = { navController.navigate(AppNavigationItem.HomeExhibitsDetail.route) }
        )
    }
}

@Composable
fun PotteryCard(
    potteryImage: Painter,
    title: String,
    description: String,
    label: String,
    selected: Boolean,
    onCheckToggle: () -> Unit,
    onDelete: () -> Unit,
    onCardClick: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(),
        modifier = Modifier.padding(12.dp),
        onClick = onCardClick,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row {
            // 이미지 섹션
            Box(modifier = Modifier.weight(1f)) {
                Image(
                    painter = potteryImage,
                    contentDescription = null,
                    modifier = Modifier.height(160.dp)
                )
                // 체크마크 (활성화/비활성화)
                IconButton(onClick = onCheckToggle,
                    modifier = Modifier
                        .size(32.dp)
                        .offset(8.dp, 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "체크",
                        tint = if (selected) Color(0xFF393e46) else Color.LightGray,
                    )
                }
            }
            Spacer(Modifier.width(16.dp))
            // 텍스트 및 액션 섹션
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(
                            text = label,
                            modifier = Modifier
                                .background(color = Color(0xFFF9F6DC), shape = RoundedCornerShape(20.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp),
                            color = Color(0xFF888800),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Spacer(Modifier.height(6.dp))
                        Text(
                            text = title,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Justify,
                            color = Color.Black
                        )
                    }
                    Row {
                        IconButton(onClick = onDelete) {
                            Icon(Icons.Default.Delete, contentDescription = "삭제")
                        }
                    }
                }
                Spacer(Modifier.height(32.dp))
                Text(
                    text = description,
                    fontSize = 13.sp,
                    color = Color(0xFF5F6074),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

