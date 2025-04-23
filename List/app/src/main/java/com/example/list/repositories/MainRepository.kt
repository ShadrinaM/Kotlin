package com.example.list.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.preference.PreferenceManager
import com.example.list.AppList
import com.example.list.R
import com.example.list.data.Faculty
import com.example.list.data.Group
import com.example.list.data.ListofFaculty
import com.example.list.data.Student
import com.example.list.database.MyDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainRepository private constructor() {

    companion object {
        private var INSTANCE: MainRepository? = null

        fun getInstance(): MainRepository{
            if (INSTANCE == null){
                INSTANCE = MainRepository()
            }
            return INSTANCE?: throw IllegalStateException("Repository not found")
        }
    }

//    var listofFaculty: MutableLiveData<ListofFaculty?> = MutableLiveData()
    var faculty: MutableLiveData<Faculty> = MutableLiveData()
    var student: MutableLiveData<Student> = MutableLiveData()
    var group: MutableLiveData<Group> = MutableLiveData()
//    fun addFaculty(faculty: Faculty) {
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


    fun saveData(){
//        val context = AppList.context
//        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//        sharedPreferences.edit().apply(){
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
//        sharedPreferences.apply() {
//            val jsonString = getString(context.getString(R.string.preference_key_faculty_list), null)
//            if (jsonString!=null){
//                val listType = object : TypeToken<List<Faculty>>() {}.type
//                val tempList = Gson().fromJson<List<Faculty>>(jsonString, listType)
//                val temp = ListofFaculty()
//                temp.items = tempList.toMutableList()
//                listofFaculty.postValue(temp)
//                }
//
//        }
    }

    private val listDB by lazy {OfflineDBRepository(MyDatabase.getDatabase(AppList.context).myDAO())}
    private  val myCoroutineScope = CoroutineScope(Dispatchers.Main)

    fun onDestroy(){
        myCoroutineScope.cancel()
    }

    val listofFaculty:LiveData<List<Faculty>> = listDB.getFaculty()
//        .onStart { emit loading state }
//        .catch { exeption -> emit error state }
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
        if (position<0||(listofFaculty.value?.size!!<=position))
            return
        setCurrentFaculty(listofFaculty.value!![position])
    }

    fun setCurrentFaculty(_faculty: Faculty){
        faculty.postValue(_faculty) // чтобы не использовать this делают с нижними подчёркиванием
    }


    val listofStudent:LiveData<List<Student>> = listDB.getAllStudents()
        .asLiveData()

    fun addStudent(student: Student){
        myCoroutineScope.launch {
            listDB.insertStudent(student)
            setCurrentStudent(student)
        }
    }
    fun updateStudent(student: Student){
        addStudent(student)
    }

    fun deleteStudent(student: Student){
        myCoroutineScope.launch {
            listDB.deleteStudent(student)
            setCurrentStudent(0)
        }
    }

    fun setCurrentStudent(position: Int){
        if (position<0||(listofStudent.value?.size!!<=position))
            return
        setCurrentStudent(listofStudent.value!![position])
    }

    fun setCurrentStudent(_student: Student){
        student.postValue(_student) // чтобы не использовать this делают с нижними подчёркиванием
    }

    val listofGroup:LiveData<List<Group>> = listDB.getAllGroups()
        .asLiveData()

    val facultyGroups
        get() = {
            if (faculty.value != null)
                listDB.getFacultyGroups(faculty.value!!.id)
            else emptyList()
        }
    fun addGroup(group: Group){
        myCoroutineScope.launch {
            listDB.insertGroup(group)
            setCurrentGroup(group)
        }
    }
    fun updateGroup(group: Group){
        addGroup(group)
    }

    fun deleteGroup(group: Group){
        myCoroutineScope.launch {
            listDB.deleteGroup(group)
            setCurrentGroup(0)
        }
    }

    fun setCurrentGroup(position: Int){
        if (position<0||(listofGroup.value?.size!!<=position))
            return
        setCurrentGroup(listofGroup.value!![position])
    }

    fun setCurrentGroup(_group: Group){
        group.postValue(_group) // чтобы не использовать this делают с нижними подчёркиванием
    }
}