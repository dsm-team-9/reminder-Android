package com.example.reminder_android.presentation.feature.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.reminder_android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontSynthesis
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.example.reminder_android.ToggleMajors
import com.example.reminder_android.presentation.AppNavigationItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Column {
        TopProfile(
            modifier = Modifier.fillMaxWidth(),
            iconSize = 56.dp,
            title = "홍길동",
        )
        Column(
            modifier = Modifier
                .background(color = Color(0xFFF2F2F2))
                .fillMaxSize()
                .padding(start = 30.dp, end = 30.dp),
        ) {
            ToggleMajors(onMajorSelected = { /* TODO: Handle major selection */ })
            MuseumItem(
                modifier = Modifier.padding(top = 20.dp),
                imageUrl = "",
                itemCount = 0,
                nickName = "홍길동",
                navController = navController,
                appRoute = AppNavigationItem.HomeDetail.route,
            )
        }
    }
}

@Composable
internal fun MuseumItem(
    modifier: Modifier = Modifier,
    imageUrl: String,
    itemCount: Int,
    nickName: String,
    navController: NavController,
    appRoute: String,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContainerColor = Color.White,
            disabledContentColor = Color.Black,
        ),
        onClick = { navController.navigate(AppNavigationItem.HomeDetail.route) },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(),
    ) {
        Row {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(12.dp)
            ) {
                Text(
                    text = "$nickName 님의 박물관",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.align(Alignment.TopStart)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_jar_item),
                        contentDescription = null,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = itemCount.toString(),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Composable
internal fun TopProfile(
    modifier: Modifier = Modifier,
    iconSize: Dp = 40.dp,
    title: String,
) {
    Box(
        modifier = modifier
            .background(color = Color.White)
            .fillMaxWidth()
            .height(56.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize().padding(start = 12.dp, end = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(iconSize)
                    .background(Color.White, shape = RoundedCornerShape(4.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "${title}님",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                color = Color.Black,
            )
        }
    }
}