package com.example.reminder_android

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
    MATH, SCIENCE, HISTORY, SOCIAL_STUDIES, KOREAN
}

@Composable
fun MajorPickerInlineWithScrimAndBlur(
    onMajorSelected: (MajorTag) -> Unit
) {
    var showPanel by remember { mutableStateOf(false) }
    var selectedMajor by remember { mutableStateOf<MajorTag?>(null) }

    Box {
        // 1) 배경: 블러 + 반투명 스크린
        if (showPanel) {
            Box(
                Modifier
                    .fillMaxSize()
                    .blur(16.dp) // 배경 전체에 블러 적용
                    .clickable { showPanel = false }
            )
        }

        // 2) 버튼과 인라인 패널
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.clickable {
                    showPanel = true
                },
                text = selectedMajor?.displayName ?: "선택",
                fontSize = 11.sp,
                color = selectedMajor?.color ?: Color.Black // Apply color here
            )

            Spacer(modifier = Modifier.width(8.dp))

            AnimatedVisibility(
                visible = showPanel,
                enter = expandHorizontally(expandFrom = Alignment.Start),
                exit = shrinkHorizontally(shrinkTowards = Alignment.Start)
            ) {
                Card(
                    shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    modifier = Modifier
                        .height(IntrinsicSize.Min)
                        .width(200.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text("전공 선택", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                        Divider()
                        MajorTag.values().forEach { major ->
                            Text(
                                text = major.displayName,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        selectedMajor = major
                                        onMajorSelected(major)
                                        showPanel = false
                                    }
                                    .padding(vertical = 6.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}


