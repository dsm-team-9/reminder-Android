package com.example.reminder_android

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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