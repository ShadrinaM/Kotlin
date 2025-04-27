package com.example.list.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.list.data.Faculty
import com.example.list.data.Group
import com.example.list.data.Student
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface MyDAO {
    @Query("SELECT * FROM Faculty order by faculty_name")
    fun getFaculty(): Flow<List<Faculty>>

    @Insert(entity = Faculty::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFaculty(faculty: Faculty)

    @Update(entity = Faculty::class)
    suspend fun updateFaculty(faculty: Faculty)

    @Insert(entity = Faculty::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFaculty(facultyList: List<Faculty>)

    @Delete(entity = Faculty::class)
    suspend fun deleteFaculty(faculty: Faculty)

    @Query("DELETE FROM Faculty")
    suspend fun deleteAllFaculty()

    // Для Студентов:

    @Query("SELECT * FROM students")
    fun getAllStudents(): Flow<List<Student>>

    @Query("SELECT * FROM students where group_id=:groupID")
    fun getGroupStudents(groupID: UUID): Flow<List<Student>>

    @Insert(entity = Student::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Delete(entity = Student::class)
    suspend fun deleteStudent(student: Student)

    @Query("DELETE FROM students")
    suspend fun deleteAllStudent()

    //Для Group

//    @Query("SELECT * FROM groups")
//    fun getAllGroups(): Flow<List<Group>>
//
//    @Query("SELECT * FROM groups where faculty_id=:facultyID")
//    fun getFacultyGroups(facultyID: UUID): List<Group>

//    @Query("DELETE FROM groups")
//    suspend fun deleteAllGroup()

    @Query("SELECT * FROM `groups`")  // Обратите внимание на кавычки
    fun getAllGroups(): Flow<List<Group>>

    @Query("SELECT * FROM `groups` where faculty_id=:facultyID")
    fun getFacultyGroups(facultyID: UUID): List<Group>

    @Query("DELETE FROM `groups`")
    suspend fun deleteAllGroup()

    @Insert(entity = Group::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGroup(group: Group)

    @Delete(entity = Group::class)
    suspend fun deleteGroup(group: Group)



}