package com.example.collegeschedule2.ui.schedule

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.collegeschedule2.data.dto.GroupsDto
import com.example.collegeschedule2.data.network.RetrofitInstance
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun GroupsSelecting(
    onGroupSelected: (String) -> Unit
) {
    var groups by remember { mutableStateOf<List<GroupsDto>>(emptyList()) }
    var search by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        groups = RetrofitInstance.api.getGroups()
    }

    val filtered = groups.filter {
        it.groupName.contains(search, ignoreCase = true)
    }

    Column {
        TextField(
            value = search,
            onValueChange = { search = it },
            label = { Text("Поиск группы") }
        )

        LazyColumn {
            items(filtered) { group ->
                Text(
                    text = group.groupName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onGroupSelected(group.groupName)
                        }
                        .padding(16.dp)
                )
            }
        }
    }
}
