package com.example.reminder_android.presentation.feature.main.upLoadExhibit

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.reminder_android.data.request.CreateCardRequest
import com.example.reminder_android.presentations.data.api.ApiProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun UpLoadExhibit(
    navController: NavController,
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF8F8F8)) // 배경색 변경
            .verticalScroll(rememberScrollState())
            .padding(24.dp) // 전체 패딩 유지
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(), // fillMaxHeight 대신 fillMaxWidth
            shape = RoundedCornerShape(16.dp), // 둥근 모서리
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp), // 그림자 효과
            colors = CardDefaults.cardColors(containerColor = Color.White) // 카드 배경색 흰색
        ) {
            Column(
                modifier = Modifier.padding(24.dp) // 카드 내부 패딩
            ) {
                Text(
                    text = "전시품 업로드",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold, // 폰트 굵게
                    color = Color(0xFF333333) // 진한 회색
                )
                Spacer(modifier = Modifier.height(8.dp)) // 여백 증가
                Text(
                    text = "자신의 전시품을 다른사람에게 공유해보세요",
                    style = MaterialTheme.typography.bodyMedium, // bodySmall 대신 bodyMedium
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(32.dp)) // 여백 증가

                Text(
                    text = "빈태그",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFF5CF00) // 기존 색상 유지
                )
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = { Text("이곳에 내용을 적어주세요", color = Color(0xFFAAAAAA)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp), // 높이 조정
                    maxLines = 10, // 최대 라인 수 조정
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF5F6074),
                        unfocusedBorderColor = Color(0xFFDDDDDD),
                        cursorColor = Color(0xFF5F6074)
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        CoroutineScope(Dispatchers.IO).launch {
                            kotlin.runCatching {
                                ApiProvider.cardApi.createCard(
                                    request = CreateCardRequest(
                                        title = title,
                                        content = description,
                                        category = null,
                                    )
                                )
                            }.onSuccess {
                                println(it)
                            }.onFailure {
                                Log.d("TEST", it.toString())
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp), // 버튼 높이 증가
                    shape = RoundedCornerShape(12.dp), // 버튼 둥근 모서리
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF5F6074), // 버튼 배경색
                        contentColor = Color.White // 버튼 텍스트 색상
                    )
                ) {
                    Text(text = "업로드", fontSize = 18.sp, fontWeight = FontWeight.Bold) // 버튼 텍스트 스타일
                }
            }
        }
    }
}