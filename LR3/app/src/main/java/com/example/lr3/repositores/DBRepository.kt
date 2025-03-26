package com.example.lr3.repositores

import com.example.lr3.data.Faculty
import kotlinx.coroutines.flow.Flow

interface DBRepository {
    fun getFaculty(): Flow<List<Faculty>>
    suspend fun insertFaculty(faculty: Faculty)
    suspend fun updateFaculty(faculty: Faculty)
    suspend fun insertAllFaculty(facultyList: List<Faculty>)
    suspend fun deleteFaculty(faculty: Faculty)
    suspend fun deleteAllFaculty()

}