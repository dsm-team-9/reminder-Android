package com.example.reminder_android.presentation.feature.main.my

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.reminder_android.MajorPickerInlineWithScrimAndBlur
import com.example.reminder_android.R
import com.example.reminder_android.presentation.feature.main.home.TopProfile

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyDetailScreen(
    navController: NavController,
    myViewModel: MyViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        myViewModel.fetchMyCard()
    }

    Column(
        modifier = Modifier
            .background(color = Color(0xFFF2F2F2))
            .fillMaxSize()
    ) {
        Log.d("TEST", myViewModel.myCardList.toString())
        TopProfile(
            title = "홍길동"
        )
        PotteryDetailCard(
            modifier = Modifier
                .background(color = Color(0xFFD9D9D9))
                .padding(vertical = 12.dp, horizontal = 20.dp),
            potteryImage = "",
            onConfirm = { navController.popBackStack() },
        )
    }
}

@Composable
fun PotteryDetailCard(
    modifier: Modifier = Modifier,
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
    var isEditing by remember { mutableStateOf(false) }
    var editedDescription by remember { mutableStateOf(description) }

    Box(
        modifier = modifier
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // 상단 사용자 정보와 타이틀
            Spacer(Modifier.height(16.dp))

            // 토기 이미지
            Card(
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.elevatedCardElevation(),
                modifier = Modifier
            ) {
                AsyncImage(
                    model = potteryImage,
                    contentDescription = "빗살무늬 토기 이미지",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(220.dp)
                )
            }

            Spacer(Modifier.height(12.dp))

            // 정보 구분 라벨
            Row {
                Column {
                    MajorPickerInlineWithScrimAndBlur(
                        onMajorSelected = {}
                    )

                    Spacer(Modifier.height(8.dp))

                    // 제목
                    Text(
                        text = title,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Row {
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = { isEditing = !isEditing }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "수정"
                        )
                    }
                }
            }

            Spacer(Modifier.height(4.dp))

            // 설명
            if (isEditing) {
                TextField(
                    value = editedDescription,
                    onValueChange = { editedDescription = it },
                    modifier = Modifier
                        .background(color = Color.White)
                        .fillMaxWidth(),
                )
            } else {
                Text(
                    text = editedDescription,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }

            Spacer(modifier = Modifier.padding(top = 80.dp))

            // 확인 버튼
            Button(
                onClick = onConfirm,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonColors(
                    containerColor = Color(0xFF5F6074),
                    contentColor = Color.White,
                    disabledContainerColor = Color(0xFF5F6074),
                    disabledContentColor = Color.White
                )
            ) {
                Text(
                    text = "확인",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

