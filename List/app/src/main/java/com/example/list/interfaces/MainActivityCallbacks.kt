package com.example.list.interfaces

import com.example.list.data.NameofFragment
import com.example.list.data.Student

interface MainActivityCallbacks {
    fun newTitle(_title: String)
    fun showFragment(fragmentType: NameofFragment, student: Student? = null)
}