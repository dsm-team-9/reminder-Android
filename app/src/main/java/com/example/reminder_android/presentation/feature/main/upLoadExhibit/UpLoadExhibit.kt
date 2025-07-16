package com.example.reminder_android.presentation.feature.main.upLoadExhibit

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun UpLoadExhibit(
    navController: NavController,
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(color = Color(0xFFD9D9D9))
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = "전시품 업로드",
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "자신의 전시품을 다른사람에게 공유해보세요",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "빈태그",
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFFF5CF00)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "이곳에 전시품 이름을 적어주세요",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                placeholder = { Text("이곳에 내용을 적어주세요") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                maxLines = 6,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { /* 업로드 로직 구현 */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "업로드")
            }
        }
    }

}