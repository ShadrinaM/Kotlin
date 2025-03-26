package com.example.lr3.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.lr3.data.Faculty
import kotlinx.coroutines.flow.Flow

@Dao

interface MyDAO {
    @Query("SELECT * FROM Faculty order by faculty_name")
    fun getFaculty(): Flow<List<Faculty>>

    @Insert(entity = Faculty::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFaculty(faculty: Faculty)

    @Update(entity = Faculty::class)
    suspend fun updateFaculty(faculty: Faculty)

    @Insert(entity = Faculty::class)
    suspend fun insertAllFaculty(facultyList: List<Faculty>)

    @Delete(entity =Faculty::class)
    suspend fun deleteFaculty(faculty: Faculty)

    @Query("DELETE FROM Faculty")
    suspend fun  deleteAllFacilty()
}