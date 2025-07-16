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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.reminder_android.R
import com.example.reminder_android.presentation.feature.main.home.TopProfile
import org.jetbrains.annotations.Async

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.LineHeightStyle

@Composable
fun MyDetailScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopProfile(
            title = "홍길동"
        )
        PotteryDetailCard(
            potteryImage = painterResource(R.drawable.testimg)
        ) { }

    }
}

@Composable
fun PotteryDetailCard(
    userName: String = "홍길동님",
    potteryImage: Painter,
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(color = Color.LightGray, shape = CircleShape)
            )
            Text(
                text = userName,
                modifier = Modifier.align(Alignment.CenterVertically),
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }

        Spacer(Modifier.height(16.dp))

        // 토기 이미지
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.elevatedCardElevation(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = potteryImage,
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
