package com.example.list.API

import com.example.list.Data.Faculties
import com.example.list.Data.Faculty
import com.example.list.Data.Group
import com.example.list.Data.Groups
import com.example.list.Data.Student
import com.example.list.Data.Students
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface serverAPI {
    @GET("faculties")
    fun getFaculty(): Call<Faculties>

    @Headers("Content-Type: application/json")
    @POST("faculty")
    fun postFaculty(@Body faculty: Faculty): Call<PostResult>

    @Headers("Content-Type: application/json")
    @POST("faculty")
    fun deleteFaculty(@Body faculty: Faculty): Call<PostResult>

    @GET("groups")
    fun getGroups(): Call<Groups>

    @Headers("Content-Type: application/json")
    @POST("group")
    fun postGroup(@Body group: Group): Call<PostResult>

    @GET("students")
    fun getStudents(): Call<Students>

    @Headers("Content-Type: application/json")
    @POST("students")
    fun postGroup(@Body student: Student): Call<PostResult>
}