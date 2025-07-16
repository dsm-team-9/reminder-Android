package com.example.reminder_android.presentation.feature.main.home

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

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
        PotteryCard(
            potteryImage = painterResource(R.drawable.testimg),
            title = "",
            description = "",
            label = "",
            onCardClick = { navController.navigate(AppNavigationItem.HomeExhibitsDetail.route) },
            onEdit = {}
        )
    }
}

@Composable
private fun PotteryCard(
    potteryImage: Painter,
    title: String,
    description: String,
    label: String,
    onCardClick: () -> Unit,
    onEdit: () -> Unit,
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
                // 오른쪽 하단에 추가될 요소
                Box(modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                ) {
                    IconButton(onClick = onEdit) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "수정"
                        )
                    }
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
