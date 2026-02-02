package com.example.collegeschedule2.data.api

import com.example.collegeschedule2.data.dto.GroupsDto
import com.example.collegeschedule2.data.dto.ScheduleByDateDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ScheduleApi {
    @GET("api/schedule/group/{groupName}")
    suspend fun getSchedule(
        @Path("groupName") groupName: String,
        @Query("start") start: String,
        @Query("end") end: String
    ): List<ScheduleByDateDto>

    @GET("api/groups")
    suspend fun getGroups(): List<GroupsDto>
}