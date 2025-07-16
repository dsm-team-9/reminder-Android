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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import org.jetbrains.annotations.Async

@Composable
fun HomeExhibitDetailScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .background(color = Color(0xFFD9D9D9))
            .fillMaxSize()
    ) {
        TopProfile(title = "ghdrlfehd")
        PotteryDetailCard(
            userName = "홍길동",
            potteryImage = "",
            title = "빗살무늬 토기",
            onConfirm = {},
        )
    }
}

@Composable
fun PotteryDetailCard(
    userName: String = "홍길동님",
    potteryImage: String,
    title: String = "빗살무늬 토기",
    description: String = """
        빗살무늬 토기는 신석기 시대에 사용된 대표적인 토기로,
        겉면에 빗살처럼 평행하거나 교차하는 무늬가 새겨진 것이 특징입니다.
        주요 식량을 저장하거나 조리하는 데 사용되었으며, 한반도 전역에서 출토됩니다.
        제작 방식은 손으로 빚은 후 빗살 새긴 눌러 구워 만드는 과정에 의해 완성됩니다.
        이 유물은 당시 사람들의 생활 방식과 문화 수준을 보여주는 중요한 유물입니다.
    """.trimIndent(),
    onConfirm: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // 상단 사용자 정보와 타이틀
        Spacer(Modifier.height(16.dp))

        // 토기 이미지
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.elevatedCardElevation(),
            modifier = Modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = potteryImage,
                contentDescription = "빗살무늬 토기 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(220.dp)
                    .fillMaxWidth()
            )
        }

        Spacer(Modifier.height(12.dp))

        // 정보 구분 라벨
        Text(
            text = "역사",
            modifier = Modifier
                .background(color = Color(0xFFF9F6DC), shape = RoundedCornerShape(10.dp))
                .padding(horizontal = 8.dp, vertical = 2.dp),
            color = Color(0xFF888800),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(Modifier.height(8.dp))

        // 제목
        Text(
            text = title,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(Modifier.height(4.dp))

        // 설명
        Text(
            text = description,
            fontSize = 14.sp,
            color = Color.DarkGray
        )

        Spacer(Modifier.height(16.dp))

        // 확인 버튼
        Button(
            onClick = onConfirm,
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "확인",
                fontSize = 16.sp
            )
        }
    }
}
