package com.example.reminder_android.presentation.feature.signup

import android.text.Layout
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.provider.FontsContractCompat.Columns
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.reminder_android.BottomMenu
import com.example.reminder_android.R
import com.example.reminder_android.ReminderOutlinedTextField
import com.example.reminder_android.presentation.AppNavigationItem

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    signUpViewModel: SignUpViewModel = viewModel(),
) {
    val phone by remember { mutableStateOf("") }
    val nickname by remember { mutableStateOf("") }
    val password by remember { mutableStateOf("") }
    val passwordCheck by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .padding(top = 40.dp, bottom = 28.dp, start = 28.dp, end = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Topbar(navController = navController)
        Text(text = "새 계정을 만들어 서비스를 시작하세요")
        ReminderOutlinedTextField(
            modifier = Modifier.align(Alignment.Start),
            values = phone,
            titleString = "전화번호"
        )
        ReminderOutlinedTextField(
            modifier = Modifier.align(Alignment.Start),
            values = nickname,
            titleString = "닉네임"
        )
        ReminderOutlinedTextField(
            modifier = Modifier.align(Alignment.Start),
            values = password,
            titleString = "비밀번호",
            isPasswordMode = true
        )
        ReminderOutlinedTextField(
            modifier = Modifier.align(Alignment.Start),
            values = passwordCheck,
            titleString = "비밀번호 확인",
            isPasswordMode = true
        )
        Spacer(modifier = Modifier.padding(bottom = 32.dp))
        TextButton(
            modifier = Modifier.background(color = Color(0xFF5F6074), shape = RoundedCornerShape(10.dp)),
            onClick = { navController.navigate(AppNavigationItem.SignIn.route) },
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "확인",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.White,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = buildAnnotatedString {
                append("이미 계정이 있으신가요? ")

                pushStyle(SpanStyle(color = Color(0xFF9000FF), fontWeight = FontWeight.Bold))
                append("로그인")
            },
            modifier = Modifier.clickable {
                navController.navigate(AppNavigationItem.SignIn.route)
            },
            style = TextStyle(color = Color.Black) // 기본 텍스트 스타일
        )
    }
}

@Composable
fun Topbar(
    navController: NavController
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(painter = painterResource(R.drawable.ic_back_arrow), contentDescription = "")
        }
        Text(
            modifier = Modifier.padding(start = 18.dp),
            text = "회원가입",
            textAlign = TextAlign.Center
        )
    }
}