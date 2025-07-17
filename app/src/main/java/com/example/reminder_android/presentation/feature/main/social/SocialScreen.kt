package com.example.reminder_android.presentation.feature.main.social

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.reminder_android.R
import com.example.reminder_android.data.response.SearchUserNameResponse
import com.example.reminder_android.presentation.feature.main.home.TopProfile
import com.example.reminder_android.presentations.data.api.ApiProvider

import androidx.compose.runtime.collectAsState
import com.example.reminder_android.data.request.SignUpRequest
import com.example.reminder_android.presentation.AppNavigationItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SocialScreen(
    navController: NavController,
    socialViewModel: SocialViewModel = viewModel()
) {
    var searchQuery by remember { mutableStateOf("") }
    val searchResults by socialViewModel.searchResults.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(searchQuery) {
        socialViewModel.fetchFollowUser()
        if (searchQuery.isNotBlank()) {
            delay(500L) // 0.5초 디바운싱
            socialViewModel.searchUser(searchQuery)
        } else {
            socialViewModel.searchUser("") // 검색어가 비어있으면 ViewModel에 빈 쿼리 전달
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF2F2F2))
    ) {
        TopProfile(title = "홍길동")
        NicknameSearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it },
            modifier = Modifier.padding(top = 24.dp, start = 20.dp, end = 20.dp),
        )
        if (searchResults.isEmpty()) {
            Text(
                modifier = Modifier.padding(start = 24.dp),
                text = "이곳에 결과가 표시됩니다",
                color = Color(0xFFA1A2A4),
                fontSize = 16.sp,
            )
        }
        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(searchResults) { result ->
                SearchUserRow(
                    modifier = Modifier.padding(vertical = 36.dp, horizontal = 24.dp),
                    userName = result.name,
                    bottleCount = result.cardCount,
                    onFollow = {
                        CoroutineScope(Dispatchers.IO).launch {
                            kotlin.runCatching {
                                ApiProvider.userApi.followUser(
                                    userId = result.id
                                )
                            }.onSuccess {
                                println(it)
                            }.onFailure {
                                Log.d("TEST", it.toString())
                            }
                        }
                    },
                )
            }
        }
        Text(
            modifier = Modifier.padding(start = 24.dp, top = 60.dp),
            text = "팔로우 목록",
            color = Color.Black,
            fontSize = 20.sp
        )
        LazyColumn(

        ) {
            items(socialViewModel.followList) {
                FollowUserRow(
                    userName = it.name,
                    bottleCount = it.cardCount,
                    onUnfollow = {
                        CoroutineScope(Dispatchers.IO).launch {

                        }
                    },
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 24.dp)
                )
            }
        }
    }
}

@Composable
fun NicknameSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = {
                Text(
                    text = "팔로우할 닉네임을 적어주세요",
                    fontSize = 14.sp,
                    color = Color(0xFFBBBBBB)
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "검색 아이콘",
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .size(20.dp),
                    tint = Color(0xFFBBBBBB)
                )
            },
            singleLine = true,
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
            modifier = modifier
                .fillMaxWidth()
                .height(48.dp)
        )
    }
}

@Composable
fun SearchUserRow(
    userName: String,
    bottleCount: Int,
    onFollow: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFF8F8F8), shape = RoundedCornerShape(12.dp))
            .padding(vertical = 18.dp, horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 사용자 이름
        Text(
            text = userName,
            fontSize = 15.sp,
            color = Color(0xFF222222),
            modifier = Modifier.weight(1f)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            // 병(주점) 아이콘
            Icon(
                painter = painterResource(id = R.drawable.ic_jar_item), // 아이콘 리소스 필요
                contentDescription = "주점 병",
                tint = Color(0xFF222222),
                modifier = Modifier.size(22.dp)
            )
            Spacer(Modifier.width(4.dp))
            // 병 개수
            Text(
                text = bottleCount.toString(),
                color = Color(0xFF222222),
                fontSize = 14.sp,
            )
            Spacer(Modifier.width(22.dp))
            // 팔로우 취소(클릭 가능, 빨간색)
            IconButton(onClick = onFollow) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "추가",
                    tint = Color(0xFF2D2D3A)
                )
            }
        }
    }
}

@Composable
fun FollowUserRow(
    userName: String,
    bottleCount: Int,
    onUnfollow: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFF8F8F8), shape = RoundedCornerShape(12.dp))
            .padding(vertical = 18.dp, horizontal = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 사용자 이름
        Text(
            text = userName,
            fontSize = 15.sp,
            color = Color(0xFF222222),
            modifier = Modifier.weight(1f)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            // 병(주점) 아이콘
            Icon(
                painter = painterResource(id = R.drawable.ic_jar_item), // 아이콘 리소스 필요
                contentDescription = "주점 병",
                tint = Color(0xFF222222),
                modifier = Modifier.size(22.dp)
            )
            Spacer(Modifier.width(4.dp))
            // 병 개수
            Text(
                text = bottleCount.toString(),
                color = Color(0xFF222222),
                fontSize = 14.sp,
            )
            Spacer(Modifier.width(22.dp))
            // 팔로우 취소(클릭 가능, 빨간색)
            Text(
                text = "팔로우 취소",
                color = Color(0xFFFF4848),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .clickable { onUnfollow() }
                    .padding(vertical = 2.dp)
            )
        }
    }
}
