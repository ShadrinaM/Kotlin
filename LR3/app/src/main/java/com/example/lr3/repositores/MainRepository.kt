package com.example.lr3.repositores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.lr3.AppList
import com.example.lr3.data.Faculty
import com.example.lr3.data.ListofFaculty
import com.example.lr3.database.MyDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch


class MainRepository private constructor() {

    companion object {
        private var INSTANCE: MainRepository?=null

        fun getInstance(): MainRepository{
            if (INSTANCE == null){
                INSTANCE = MainRepository()
            }
            return INSTANCE?: throw IllegalStateException("Репозиторий не инициализирован")
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


    private val listDB by lazy {OffineDBRepository(MyDatabase.getDatabase(AppList.context).MyDAO())}

    private val myCoroutineScope = CoroutineScope(Dispatchers.Main)

    fun onDestroy(){
        myCoroutineScope.cancel()
    }

    val listOfFaculty: LiveData<List<Faculty>> = listDB.getFaculty()
        .asLiveData()

    fun addFaculty(faculty: Faculty){
        myCoroutineScope.launch {
            listDB.insertFaculty(faculty)
            setCurrentFaculty(faculty)
        }
    }
    fun updateFaculty(faculty: Faculty){
        addFaculty(faculty)
    }
    fun deleteFaculty(faculty: Faculty){
        myCoroutineScope.launch {
            listDB.deleteFaculty(faculty)
            setCurrentFaculty(0)
        }
    }

    fun setCurrentFaculty(position: Int){
        if (position < 0 || (listofFaculty.value?.items?.size!!<=position))
            return
        setCurrentFaculty(listofFaculty.value?.items!![position])
    }

    fun setCurrentFaculty(_faculty: Faculty){
        faculty.postValue(_faculty)
    }
}