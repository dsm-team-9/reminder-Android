package com.example.reminder_android.presentation.feature.main.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.reminder_android.R

@Composable
fun ChatAIDetailScreen(
    navController: NavController
) {
    val duDate = listOf(Pair("ddddd", true), Pair("ddddd", false))
    Column(
        modifier = Modifier
            .background(color = Color(0xFFA1A2A4))
            .fillMaxSize()
    ) {
        PotteryChatScreen(
            messages = duDate,
            currentInput = "ㅇ노리",
            onInputChange = {},
            onSendClick = {},
            onBackClick = {
                navController.popBackStack()
            }
        )
    }
}

@Composable
fun PotteryChatScreen(
    messages: List<Pair<String, Boolean>>,
    currentInput: String,
    onInputChange: (String) -> Unit,
    onSendClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F7F7))
    ) {
        // 상단 바
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp, 18.dp, 18.dp, 0.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.testimg),
                contentDescription = "빗살무늬 토기",
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
            )
            Spacer(Modifier.width(14.dp))
            Text(
                text = "빗살무늬 토기",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF332F4D)
            )
            Spacer(Modifier.weight(1f))
            Icon(
                modifier = Modifier.clickable {

                },
                imageVector = Icons.Default.Close,
                contentDescription = "닫기",
                tint = Color(0xFF332F4D),
            )
        }
        Divider(
            color = Color(0xFF332F4D),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 12.dp)
        )

        // 채팅 메시지 영역
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 18.dp)
                .verticalScroll(rememberScrollState())
        ) {
            messages.forEach { (text, isReply) ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    if (isReply) {
                        Spacer(Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color(0xFFF5F1DF),
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(vertical = 7.dp, horizontal = 16.dp)
                        ) {
                            Text(
                                text = "이순신이순신",
                                color = Color(0xFF9B934E),
                                fontSize = 17.sp
                            )
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(16.dp)
                                )
                                .padding(vertical = 7.dp, horizontal = 18.dp)
                        ) {
                            Text(
                                text = text,
                                color = Color(0xFF332F4D),
                                fontSize = 17.sp
                            )
                        }
                        Spacer(Modifier.weight(1f))
                    }
                }
                Spacer(Modifier.height(6.dp))
            }
        }

        // 입력 박스
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp, 8.dp)
                .background(Color.White, RoundedCornerShape(22.dp))
                .border(1.dp, Color(0xFFE5E5E5), RoundedCornerShape(22.dp))
                .padding(start = 12.dp)
        ) {
            TextField(
                value = currentInput,
                onValueChange = onInputChange,
                placeholder = {
                    Text(
                        "메시지를 입력하세요",
                        color = Color(0xFFC4C4C4)
                    )
                },
                colors = TextFieldDefaults.colors(
                ),
                modifier = Modifier.weight(1f),
                singleLine = true,
                maxLines = 1
            )
            IconButton(onClick = onSendClick) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send",
                    tint = Color(0xFFC4C4C4)
                )
            }
        }
    }
}
