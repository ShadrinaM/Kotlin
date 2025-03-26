package com.example.lr3.repositores

import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import com.example.lr3.AppList
import com.example.lr3.data.Faculty
import com.example.lr3.data.ListofFaculty
import com.example.lr3.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class MainRepository private constructor() {

    companion object {
        private var INSTANCE: MainRepository?=null

        fun getInstance(): MainRepository{
            if (INSTANCE == null){
                INSTANCE = MainRepository()
            }
            return INSTANCE?: throw IllegalStateException("Repository not found")
        }
    }

    var listofFaculty: MutableLiveData<ListofFaculty?> = MutableLiveData()
    var faculty: MutableLiveData<Faculty> = MutableLiveData()

//    fun addFaculty (faculty: Faculty){
//        val listTmp = (listofFaculty.value ?: ListofFaculty()).apply {
//            items.add(faculty)
//        }
//        listofFaculty.postValue(listTmp)
//        setCurrentFaculty(faculty)
//    }
//
//    fun updateFaculty(faculty: Faculty){
//        val position = getFacultyPosition(faculty)
//        if(position<0) addFaculty(faculty)
//        else {
//            val listTmp = listofFaculty.value!!
//            listTmp.items[position] = faculty
//            listofFaculty.postValue(listTmp)
//        }
//    }
//
//    fun deleteFaculty(faculty: Faculty){
//        val listTmp = listofFaculty.value!!
//        if (listTmp.items.remove(faculty)){
//            listofFaculty.postValue(listTmp)
//        }
//        setCurrentFaculty(0)
//    }
//
//    fun getFacultyPosition(faculty: Faculty):Int = listofFaculty.value?.items?.indexOfFirst {
//        it.id == faculty.id } ?:-1
//
//    fun getFacultyPosition() = getFacultyPosition(faculty.value?:Faculty())
//
//    fun setCurrentFaculty(position: Int){
//        if (listofFaculty.value == null || position < 0 ||
//            (listofFaculty.value?.items?.size!!<=position))
//            return
//        setCurrentFaculty(listofFaculty.value?.items!![position])
//    }
//
//    fun setCurrentFaculty(_faculty: Faculty){
//        faculty.postValue(_faculty) // чтобы не использовать this делают с нижними подчёркиванием
//    }

    fun saveData(){
//        val context = AppList.context
//        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//        sharedPreferences.edit().apply{
//            val gson = Gson()
//            val lst = listofFaculty.value?.items?: listOf<Faculty>()
//            val jsonString = gson.toJson(lst)
//            putString(context.getString(R.string.preference_key_faculty_list),
//                jsonString)
//            apply()
//        }
    }

    fun loadData(){
//        val context = AppList.context
//        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//        sharedPreferences.apply {
//            val jsonString = getString(context.getString(R.string.preference_key_faculty_list), null)
//            if (jsonString!=null){
//                val listType = object : TypeToken<List<Faculty>>() {}.type
//                val templist= Gson().fromJson<List<Faculty>>(jsonString, listType)
//                val temp = ListofFaculty()
//                temp.items = templist.toMutableList()
//                listofFaculty.postValue(temp)
//            }
//        }
    }


    

}