package com.example.collegeschedule2.ui.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

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
        modifier = modifier
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
                            containerColor = Color(0xFFFFF9C4) // светло-желтый
                        )
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {

                            Text(
                                text = "Пара ${lesson.lessonNumber} · ${lesson.time}",
                                style = MaterialTheme.typography.labelLarge
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            lesson.groupParts.forEach { (part, info) ->
                                if (info != null) {

                                    // определяем эмодзи для предмета
                                    val lower = info.subject.lowercase()
                                    val emoji = when {
                                        listOf(
                                            "информатика", "программирование", "базы данных", "алгоритмы",
                                            "ос и сети", "проектирование по", "цифровая грамотность",
                                            "мобильная разработка", "веб-разработка", "искусственный интеллект",
                                            "машинное обучение", "робототехника", "интернет вещей", "кибербезопасность"
                                        ).any { lower.contains(it) } -> "💻"

                                        listOf(
                                            "русский язык", "литература", "иностранный язык", "английский язык",
                                            "французский язык", "немецкий язык", "испанский язык",
                                            "психология", "правоведение", "социология", "этика", "философия",
                                            "маркетинг", "экономика", "управление проектами", "дизайн интерфейсов"
                                        ).any { lower.contains(it) } -> "📚"

                                        listOf(
                                            "математика", "логика", "дискретная математика",
                                            "теория вероятностей", "статистика"
                                        ).any { lower.contains(it) } -> "🧮"

                                        else -> "🎓"
                                    }

                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column {
                                            Text(
                                                text = "$emoji ${info.subject}",
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
                                        }
                                    }

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
