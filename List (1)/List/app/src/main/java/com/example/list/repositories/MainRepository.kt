package com.example.list.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.preference.PreferenceManager
import com.example.list.AppList
import com.example.list.Data.Faculty
import com.example.list.Data.Group
import com.example.list.Data.ListOfFaculty
import com.example.list.Data.Student
import com.example.list.R
import com.example.list.database.MyDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainRepository private constructor() {

    companion object {
        private var INSTANCE: MainRepository? = null

        fun getInstance(): MainRepository {
            if (INSTANCE == null) {
                INSTANCE = MainRepository()
            }
            return INSTANCE ?: throw IllegalStateException("Репозиторий не инициализирован!")
        }
    }

//    var listOfFaculty : MutableLiveData<ListOfFaculty> = MutableLiveData()
    var faculty: MutableLiveData<Faculty> = MutableLiveData()
    var student: MutableLiveData<Student> = MutableLiveData()
    var group: MutableLiveData<Group> = MutableLiveData()

//    fun addFaculty(faculty: Faculty) {
//        val listTmp = (listOfFaculty.value ?: ListOfFaculty()).apply {
//            items.add(faculty)
//        }
//        listOfFaculty.postValue(listTmp)
//        setCurrentFaculty(faculty)
//    }
//
//    fun updateFaculty(faculty: Faculty) {
//        val position = getFacultyPosition(faculty)
//        if (position < 0) addFaculty(faculty)
//        else {
//            val listTmp = listOfFaculty.value!!
//            listTmp.items[position] = faculty
//            listOfFaculty.postValue(listTmp)
//        }
//    }
//
//    fun deleteFaculty(faculty: Faculty) {
//        val listTmp = listOfFaculty.value!!
//        if (listTmp.items.remove(faculty)) {
//            listOfFaculty.postValue(listTmp)
//        }
//        setCurrentFaculty(0)
//    }
//
//    fun getFacultyPosition(faculty: Faculty):Int = listOfFaculty.value?.items?.indexOfFirst {
//        it.id ==faculty.id} ?: -1
//
//    fun getFacultyPosition()=getFacultyPosition(faculty.value?: Faculty())
//
//    fun setCurrentFaculty(position: Int) {
//        if (listOfFaculty.value == null || position < 0 ||
//            (listOfFaculty.value?.items?.size!! <= position))
//            return
//        setCurrentFaculty(listOfFaculty.value?.items!![position])
//    }



    fun saveData(){
//        val context = AppList.context
//        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//        sharedPreferences.edit().apply{
//            val gson = Gson()
//            val lst = listOfFaculty.value?.items ?: listOf<Faculty>()
//            val jsonString = gson.toJson(lst)
//            putString(context.getString(R.string.preference_key_faculty_list),
//                jsonString)
//            apply()
//        }
    }

    fun loadData() {
//        val context = AppList.context
//        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
//        sharedPreferences.apply {
//            val jsonString = getString(context.getString(R.string.preference_key_faculty_list), null)
//            if (jsonString != null) {
//                val listType = object : TypeToken<List<Faculty>>() {}.type
//                val tempList = Gson().fromJson<List<Faculty>>(jsonString, listType)
//                val temp = ListOfFaculty()
//                temp.items = tempList.toMutableList()
//                listOfFaculty.postValue(temp)
//            }
//        }
    }

    private  val listDB by lazy { OfflineDBRepository(MyDatabase.getDatabase(AppList.context).myDAO()) }

    private val myCoroutineScope = CoroutineScope(Dispatchers.Main)

    fun onDestroy() {
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
        if (position<0 || (listOfFaculty.value?.size!!<=position))
            return
        setCurrentFaculty(listOfFaculty.value!![position])
    }

    fun setCurrentFaculty(_faculty:Faculty) {
        faculty.postValue(_faculty) // нижнее подчеркивание - это вместо this
    }

//    Для student

val listOfStudent: LiveData<List<Student>> = listDB.getAllStudents()
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
        if (position<0 || (listOfStudent.value?.size!!<=position))
            return
        setCurrentStudent(listOfStudent.value!![position])
    }

    fun setCurrentStudent(_student: Student) {
        student.postValue(_student) // нижнее подчеркивание - это вместо this
    }

    //    Для group

    val listOfGroup: LiveData<List<Group>> = listDB.getAllGroups().asLiveData()

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
        if (position<0 || (listOfGroup.value?.size!!<=position))
            return
        setCurrentGroup(listOfGroup.value!![position])
    }

    fun setCurrentGroup(_group: Group) {
        group.postValue(_group) // нижнее подчеркивание - это вместо this
    }
}