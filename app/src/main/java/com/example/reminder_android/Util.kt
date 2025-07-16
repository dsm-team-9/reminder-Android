package com.example.reminder_android

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
internal fun ReminderOutlinedTextField(
    modifier: Modifier = Modifier,
    values: String,
    titleString: String,
    isPasswordMode: Boolean = false,
) {
    var value by remember { mutableStateOf(values) }
    var isPasswordVisible by remember { mutableStateOf(true) }

    Text(
        modifier = modifier.padding(top = 24.dp),
        text = titleString,
        color = Color(0xFF5F6074)
    )

    OutlinedTextField(
        modifier = Modifier
            .size(
                width = 383.dp,
                height = 53.dp,
            )
            .padding(top = 6.dp)
            .fillMaxWidth(),
        value = value,
        onValueChange = { value = it },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color(0xFFA1A2A4),
            unfocusedContainerColor = Color(0xFFF9FAFB),
            unfocusedTextColor = Color(0xFF5F6074),
            focusedBorderColor = Color(0xFFA1A2A4),
            focusedContainerColor = Color(0xFFF9FAFB),
            focusedTextColor = Color(0xFF5F6074),
        ),
        textStyle = TextStyle(
            fontSize = 14.sp,
            textAlign = TextAlign.Start,
            lineHeight = 40.sp
        ),
        shape = RoundedCornerShape(10.dp),
        visualTransformation = if (isPasswordVisible && isPasswordMode) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPasswordMode) {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    val icon = if (isPasswordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                    val description = if (isPasswordVisible) "Hide password" else "Show password"
                    Icon(icon, contentDescription = description)
                }
            }
        },
        singleLine = true,
    )
}

@Composable
internal fun ToggleMajors(
    modifier: Modifier = Modifier,
    onMajorSelected: (String) -> Unit,
) {
    val majors = listOf("전체", "수학", "과학", "역사", "사회", "국어")
    var selectedMajor by remember { mutableStateOf("전체") }

    FlowRow(
        modifier = modifier.padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        majors.forEach { major ->
            MajorButton(
                major = major,
                selected = selectedMajor == major,
                onClick = {
                    selectedMajor = it
                    onMajorSelected(it) // This is where you would trigger the filtering
                }
            )
        }
    }
}

@Composable
private fun MajorButton(
    major: String,
    selected: Boolean,
    onClick: (String) -> Unit
) {
    Text(
        text = major,
        fontSize = 15.sp,
        color = if (selected) Color.White else Color.Black,
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(color = if (selected) Color.Black else Color.White)
            .clickable { onClick(major) }
            .padding(horizontal = 16.dp, vertical = 4.dp)
    )
}

enum class Major {
    수학,
    과학,
    역사,
    사회,
    국어
}

data class Category(
    val name: String,
    val bgColor: Color,
    val textColor: Color,
    val imageUrl: String
)

@Composable
fun CategoryImageOnDemand(
    categories: List<Category>,
    modifier: Modifier = Modifier
) {
    var selectedIndex by remember { mutableStateOf<Int?>(null) }
    val scrollState = rememberScrollState()

    Column(modifier = modifier.fillMaxWidth()) {
        // 1) 카테고리 칩 영역
        Row(
            modifier = Modifier
                .horizontalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEachIndexed { index, cat ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(
                            color = if (index == selectedIndex) cat.bgColor else Color(0xFFF0F0F0),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            selectedIndex = if (selectedIndex == index) null else index
                        }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = cat.name,
                        fontSize = 14.sp,
                        color = if (index == selectedIndex) cat.textColor else Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // 2) 클릭했을 때만 나타나는 이미지 영역
        AnimatedVisibility(
            visible = selectedIndex != null,
            enter = fadeIn() + expandVertically(),
            exit = shrinkVertically() + fadeOut()
        ) {
            selectedIndex?.let { idx ->
                AsyncImage(
                    model = categories[idx].imageUrl,
                    contentDescription = categories[idx].name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(horizontal = 16.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
            }
        }
    }
}

