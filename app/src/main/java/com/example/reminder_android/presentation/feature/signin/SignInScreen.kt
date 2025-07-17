package com.example.reminder_android.presentation.feature.signin

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.reminder_android.ReminderOutlinedTextField
import com.example.reminder_android.data.request.SignInRequest
import com.example.reminder_android.presentation.AppNavigationItem
import com.example.reminder_android.presentations.data.api.ApiProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun SignInScreen(
    navController: NavController,
) {
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .padding(top = 58.dp, start = 28.dp, end = 28.dp, bottom = 32.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(70.dp)
                .background(color = Color.Black)
        )
        Text(
            modifier = Modifier.padding(top = 46.dp),
            text = "로그인",
            fontSize = 30.sp,
        )
        ReminderOutlinedTextField(
            modifier = Modifier.align(Alignment.Start),
            values = phone,
            titleString = "전화번호",
            onValueChange = { phone = it }
        )
        ReminderOutlinedTextField(
            modifier = Modifier.align(Alignment.Start),
            values = password,
            titleString = "비밀번호",
            isPasswordMode = true,
            onValueChange = { password = it }
        )
        Spacer(modifier = Modifier.padding(bottom = 32.dp))
        TextButton(
            modifier = Modifier.background(color = Color(0xFF5F6074), shape = RoundedCornerShape(10.dp)),
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    kotlin.runCatching {
                        ApiProvider.userApi.signIn(
                            SignInRequest(
                                phoneNumber = phone,
                                password = password,
                            )
                        )
                    }.onSuccess {
                        ApiProvider.saveToken(it.accessToken) // 토큰 저장
                        withContext(Dispatchers.Main) {
                            navController.navigate(AppNavigationItem.Main.route)
                        }
                    }.onFailure {
                        Log.d("TEST", it.toString())
                    }
                }
            },
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "로그인",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.White,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = buildAnnotatedString {
                append("계정이 없으신가요? ")

                pushStyle(SpanStyle(color = Color(0xFF9000FF), fontWeight = FontWeight.Bold))
                append("회원가입")
            },
            modifier = Modifier
                .background(Color.LightGray) // Add background to visualize clickable area
                .clickable {
                    Log.d("SignInScreen", "Text clickable triggered!")
                    navController.navigate(AppNavigationItem.SignUp.route)
                },
            style = TextStyle(color = Color.Black) // 기본 텍스트 스타일
        )
    }
}