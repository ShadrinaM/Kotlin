package com.example.list.repositories

import com.example.list.data.Faculty
import com.example.list.data.Group
import com.example.list.data.Student
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface DBRepository {
    fun getFaculty(): Flow<List<Faculty>>
    suspend fun insertFaculty(faculty: Faculty)
    suspend fun updateFaculty(faculty: Faculty)
    suspend fun insertAllFaculty(facultyList: List<Faculty>)
    suspend fun deleteFaculty(faculty: Faculty)
    suspend fun deleteAllFaculty()

    fun getAllStudents(): Flow<List<Student>>
    fun getGroupStudents(groupID: UUID): Flow<List<Student>>
    suspend fun insertStudent(student: Student)
    suspend fun deleteStudent(student: Student)
    suspend fun deleteAllStudent()

    fun getAllGroups(): Flow<List<Group>>
    fun getFacultyGroups(facultyID: UUID): List<Group>
    suspend fun insertGroup(group: Group)
    suspend fun deleteGroup(group: Group)
    suspend fun deleteAllGroup()
}