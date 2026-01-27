package com.example.collegeschedule2.data.repository

import com.example.collegeschedule2.data.api.ScheduleApi
import com.example.collegeschedule2.data.dto.ScheduleByDateDto
class ScheduleRepository(private val api: ScheduleApi) {
    suspend fun loadSchedule(group: String): List<ScheduleByDateDto> {
        return api.getSchedule(
            groupName = group,
            start = "2026-01-12",
            end = "2026-01-17"
        )
    }
}
