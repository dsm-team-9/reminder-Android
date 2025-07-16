package com.example.reminder_android.presentation.feature.main.home

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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight.Companion.Bold

@Composable
fun HomeDetailScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .background(color = Color(0xFFD9D9D9))
            .fillMaxSize()
    ) {
        TopProfile(title = "ghdrlfehd")
    }
}

@Composable
private fun PotteryCard(
    potteryImage: Painter,
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
            .height(120.dp), // Set height to 120.dp as in MuseumItem
        onClick = onCardClick
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
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
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 라벨 칩
                    Text(
                        text = label,
                        fontSize = 12.sp,
                        color = Color(0xFF888800),
                        modifier = Modifier
                            .background(Color(0xFFF9F6DC), shape = RoundedCornerShape(10.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    )

                    // 수정/삭제 버튼
                    Row {
                        IconButton(onClick = onDelete) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "삭제"
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 제목
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(6.dp))

                // 설명 텍스트
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    maxLines = 2,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}


