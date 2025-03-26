package com.example.lr3.repositores

import com.example.lr3.data.Faculty
import com.example.lr3.database.MyDAO
import kotlinx.coroutines.flow.Flow


class OffineDBRepository(val dao: MyDAO) : DBRepository {
    override fun getFaculty(): Flow<List<Faculty>> = dao.getFaculty()
    override suspend fun insertFaculty(faculty: Faculty) = dao.insertFaculty(faculty)
    override suspend fun updateFacylty(faculty: Faculty) = dao.updateFaculty(faculty)
    override suspend fun insertAllFaculty(facultyList: List<Faculty>) =
        dao.insertAllFaculty(facultyList)

    override suspend fun deleteFaculty(faculty: Faculty) = dao.deleteFaculty(faculty)
    override suspend fun deleteAllFaculty() = dao.deleteAllFacilty()
}