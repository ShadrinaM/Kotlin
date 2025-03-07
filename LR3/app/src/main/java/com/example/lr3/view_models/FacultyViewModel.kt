package com.example.lr3.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.lr3.data.Faculty
import com.example.lr3.data.ListofFaculty
import com.example.lr3.repositores.MainRepository

class FacultyViewModel : ViewModel() {
    var facultyList: MutableLiveData<ListofFaculty> = MutableLiveData()
    private var _faculty: Faculty? = null
    val faculty
        get()=_faculty
    private val facultyListObserver = Observer<ListofFaculty?>{
            list->
        facultyList.postValue(list)
    }

    init{
        MainRepository.getInstance().listofFaculty.observeForever(facultyListObserver)
        MainRepository.getInstance().faculty.observeForever{
            _faculty=it
        }
    }

    fun deleteFaculty(){
        if (faculty!=null)
            MainRepository.getInstance().deleteFaculty(faculty!!)
    }

    fun appendFaculty(facultyName: String){
        val faculty = Faculty()
        faculty.name = facultyName
        MainRepository.getInstance().addFaculty(faculty)
    }

    fun updateFaculty(facultyName: String){
        if(_faculty!=null){
            _faculty!!.name=facultyName
            MainRepository.getInstance().updateFaculty(_faculty!!)
        }
    }

    fun setCurrentFaculty(faculty: Faculty){
        MainRepository.getInstance().setCurrentFaculty(faculty)
    }
}