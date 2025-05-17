package com.example.list.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.list.Data.Group
import com.example.list.Data.Student
import com.example.list.repositories.MainRepository
import kotlinx.coroutines.launch
import java.util.Date

class StudentsListViewModel : ViewModel() {
    private val _currentGroup = MutableLiveData<Group>()
    private val _students = MutableLiveData<List<Student>>()

    val studentList: LiveData<List<Student>> = _students

    private fun loadStudentsForGroup() {
        _currentGroup.value?.let { group ->
            viewModelScope.launch {
                MainRepository.getInstance().getByGroupStudents(group.id).collect { students ->
                    _students.postValue(students)
                }
            }
        }
    }

    //var studentList = MainRepository.getInstance().listOfStudent
    private var _student: Student? = null
    val student
        get() = _student
    init {
        MainRepository.getInstance().student.observeForever {
            _student = it
        }
    }

    var group: Group? = null
    fun set_Group(group: Group) {
        this.group = group
        _currentGroup.value = group
        loadStudentsForGroup()
    }

    fun deleteStudent() {
        if (student != null)
            MainRepository.getInstance().deleteStudent(student!!)
    }

    fun appendStudent(
        lastName: String,
        firstName: String,
        middleName: String,
        birthDate: Date,
        phone: String
    ) {
        val student = Student()
        student.lastName = lastName
        student.firstName = firstName
        student.middleName = middleName
        student.birthDate = birthDate
        student.phone = phone
        student.groupID = group!!.id
        MainRepository.getInstance().addStudent(student)
    }

    fun updateStudent(
        lastName: String,
        firstName: String,
        middleName: String,
        birthDate: Date,
        phone: String
    ) {
        if (_student != null) {
            _student!!.lastName = lastName
            _student!!.firstName = firstName
            _student!!.middleName = middleName
            _student!!.birthDate = birthDate
            _student!!.phone = phone
            MainRepository.getInstance().updateStudent(_student!!)
        }
    }

    fun setCurrentStudent(student: Student) {
        MainRepository.getInstance().setCurrentStudent(student)
    }
}