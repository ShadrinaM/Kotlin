package com.example.lr3.repositores

import android.icu.text.Transliterator.Position
import androidx.lifecycle.MutableLiveData
import com.example.lr3.data.Faculty
import com.example.lr3.data.ListOfFaculty

class MainRepository private constructor(){

    companion object{
        private  var INSTANCE: MainRepository?=null

        fun getInstance(): MainRepository{
            if (INSTANCE == null){
                INSTANCE = MainRepository()
            }
            return INSTANCE?: throw IllegalStateException("Репозиторий не идентифицирован")
        }
    }

    val listOfFaculty: MutableLiveData<ListOfFaculty?> = MutableLiveData()
    var faculty: MutableLiveData<Faculty> = MutableLiveData()

    fun addFaculty(faculty: Faculty){
        val listTemp = (listOfFaculty.value ?: ListOfFaculty()).apply {
            items.add(faculty)
        }
        listOfFaculty.postValue(listTemp)
        setCurrentFaculty(faculty)
    }

    fun updateFaculty(faculty: Faculty){
        val position = getFacultyPosition(faculty)
        if (position<0) addFaculty(faculty)
        else {
            val listTemp = listOfFaculty.value!!
            listTemp.items[position]=faculty
            listOfFaculty.postValue(listTemp)
        }
    }

    fun deleteFaculty(faculty: Faculty){
        val listTemp = listOfFaculty.value!!
        if (listTemp.items.remove(faculty)){
            listOfFaculty.postValue((listTemp))
        }
        setCurrentFaculty(0)
    }

    fun getFacultyPosition(faculty: Faculty): Int = listOfFaculty.value?.items?.indexOfFirst {
        it.id==faculty.id}?:1

    fun getFacultyPosition()=getFacultyPosition(faculty.value?: Faculty())

    fun setCurrentFaculty(position: Int){
        if (listOfFaculty.value ==null || position < 0 ||
            (listOfFaculty.value?.items?.size!!<=position))
            return
        setCurrentFaculty(listOfFaculty.value?.items!![position])
    }

    fun setCurrentFaculty(_faculty: Faculty){
        faculty.postValue((_faculty))
    }
}
