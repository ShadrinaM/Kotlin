package com.example.list.view_models

import androidx.lifecycle.ViewModel
import com.example.list.data.Group
import com.example.list.repositories.MainRepository

class GroupViewModel : ViewModel() {
    var groupList = MainRepository.getInstance().listofGroup

    private var _group: Group?= null
    val group
        get()=_group
    init{

        MainRepository.getInstance().group.observeForever{
            _group = it
        }
    }

    fun deleteGroup(){
        if (group != null)
            MainRepository.getInstance().deleteGroup(group!!)
    }

    fun appendGroup(groupName: String){
        val group = Group()
        group.name=groupName
        group.facuktyID = faculty?.id
        MainRepository.getInstance().addGroup(group)
    }

    fun updateGroup(groupName: String){
        if (_group!=null){
            _group!!.name=groupName
            MainRepository.getInstance().updateGroup(_group!!)
        }
    }

    fun setCurrentGroup(position: Int){
        if ((groupList.value?.size ?: 0)>position)
            groupList.value?.let{MainRepository.getInstance().setCurrentGroup(it.get(position))}
    }

    fun setCurrentGroup(group: Group){
        MainRepository.getInstance().setCurrentGroup(group)
    }

    val getGroupListPosition
        get() = groupList.value?.indexOfFirst { it.id==group?.id }?:-1
    val faculty
        get() = MainRepository.getInstance().faculty.value
    val groups
        get() = MainRepository.getInstance().facultyGroups
}