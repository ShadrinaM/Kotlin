package com.example.list.view_models

import android.nfc.Tag
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.list.Data.Faculty
import com.example.list.Data.Group
import com.example.list.repositories.MainRepository
import kotlinx.coroutines.launch

class GroupViewModel : ViewModel() {

    var groupList = MainRepository.getInstance().listOfGroup

    private var _group : Group? = null
    val group
        get()=_group

    init {
        MainRepository.getInstance().group.observeForever{
            _group=it
        }
    }

    fun deleteGroup() {
        if (group!=null)
            MainRepository.getInstance().deleteGroup(group!!)
    }

    // изменено для сервера
    fun appendGroup(groupName : String) {
        val group = Group()
        group.name = groupName
        group.facultyID = faculty?.id?: -1L
        MainRepository.getInstance().addGroup(group)
    }

    fun updateGroup(groupName: String) {
        if (_group != null) {
            _group!!.name=groupName
            MainRepository.getInstance().updateGroup(_group!!)
        }
    }
    fun setCurrentGroup(position: Int){
        if ((groupList.value?.size ?: 0) > position)
            groupList.value?.let {MainRepository.getInstance().setCurrentGroup(it.get(position))}
    }
    fun setCurrentGroup(group: Group){
        MainRepository.getInstance().setCurrentGroup(group)
    }

    val getGroupListPosition
        get()= groupList.value?.indexOfFirst { it.id == group?.id } ?: -1

    val faculty
        get()=MainRepository.getInstance().faculty.value
    val groups
        get()=MainRepository.getInstance().facultyGroups
}