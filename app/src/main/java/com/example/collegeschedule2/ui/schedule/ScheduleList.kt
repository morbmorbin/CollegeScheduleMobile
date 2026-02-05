package com.example.collegeschedule2.ui.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.collegeschedule2.data.dto.ScheduleByDateDto
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CardDefaults

@Composable
fun ScheduleList(
    data: List<ScheduleByDateDto>,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(data) { day ->

            Text(
                text = "${day.lessonDate} • ${day.weekday}",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            if (day.lessons.isEmpty()) {
                Text(
                    text = "Занятий нет",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            } else {

                day.lessons.forEach { lesson ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = "Пара ${lesson.lessonNumber} · ${lesson.time}",
                                style = MaterialTheme.typography.labelLarge
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            lesson.groupParts.forEach { (part, info) ->
                                if (info != null) {

                                    Text(
                                        text = info.subject,
                                        style = MaterialTheme.typography.titleMedium
                                    )

                                    Text(
                                        text = info.teacher,
                                        style = MaterialTheme.typography.bodyMedium
                                    )

                                    Text(
                                        text = "${info.building}, аудитория ${info.classroom}",
                                        style = MaterialTheme.typography.bodySmall
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
