package com.example.reminder_android.presentation.feature.main.social

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.reminder_android.data.response.SearchUserNameResponse
import com.example.reminder_android.presentations.data.api.ApiProvider
import kotlinx.coroutines.launch

@Composable
fun SocialScreen(
    navController: NavController
) {
    var searchQuery by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf<List<SearchUserNameResponse>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier
        .background(color = Color(0xFFF2F2F2))
        .padding(16.dp)) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                coroutineScope.launch {
                    if (searchQuery.isNotBlank()) {
                        searchResults = ApiProvider.socialApi.searchUserName(searchQuery)
                    }
                }
            },
            label = { Text("Search by name") },
            modifier = Modifier.fillMaxWidth()
        )

        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(searchResults) { result ->
                Text(text = result.name, modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}

