package com.example.list

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.replace
import com.example.list.data.NameofFragment
import com.example.list.data.Student
import com.example.list.fragments.FacultyFragment
import com.example.list.fragments.GroupFragment
import com.example.list.interfaces.MainActivityCallbacks
import com.example.list.repositories.MainRepository

class MainActivity : AppCompatActivity(),MainActivityCallbacks {

    interface Edit{
        fun append()
        fun update()
        fun delete()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        showFragment(NameofFragment.FACULTY)
    }

    private var _miNewFaculty: MenuItem? = null
    private var _miChangeFaculty: MenuItem? = null
    private var _miDeleteFaculty: MenuItem? = null
    private var _miAppendGroup: MenuItem? = null
    private var _miUpdateGroup: MenuItem? = null
    private var _miDeleteGroup: MenuItem? = null

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        _miNewFaculty = menu?.findItem(R.id.miNewFaculty)
        _miChangeFaculty = menu?.findItem(R.id.miChangeFaculty)
        _miDeleteFaculty = menu?.findItem(R.id.miDeleteFaculty)

        _miAppendGroup = menu?.findItem(R.id.miAppendGroup)
        _miUpdateGroup = menu?.findItem(R.id.miUpdateGroup)
        _miDeleteGroup = menu?.findItem(R.id.miDeleteGroup)
        updateMenu(activeFragment)
        return true
    }

    var activeFragment: NameofFragment=NameofFragment.FACULTY

    private fun updateMenu(fragmentType: NameofFragment){
        _miNewFaculty?.isVisible = fragmentType ==NameofFragment.FACULTY
        _miChangeFaculty?.isVisible = fragmentType ==NameofFragment.FACULTY
        _miDeleteFaculty?.isVisible = fragmentType ==NameofFragment.FACULTY
        _miAppendGroup?.isVisible = fragmentType ==NameofFragment.GROUP
        _miUpdateGroup?.isVisible = fragmentType ==NameofFragment.GROUP
        _miDeleteGroup?.isVisible = fragmentType ==NameofFragment.GROUP
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.miNewFaculty->{
                FacultyFragment.getInstance().append()
                true
            }
            R.id.miChangeFaculty->{
                FacultyFragment.getInstance().update()
                true
            }
            R.id.miDeleteFaculty->{
                FacultyFragment.getInstance().delete()
                true
            }

            R.id.miAppendGroup->{
                GroupFragment.getInstance().append()
                true
            }
            R.id.miUpdateGroup->{
                GroupFragment.getInstance().update()
                true
            }
            R.id.miDeleteGroup->{
                GroupFragment.getInstance().delete()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showFragment(fragmentType: NameofFragment, student: Student?){

        when (fragmentType){
            NameofFragment.FACULTY->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fcvMain, FacultyFragment.getInstance())
                    .addToBackStack(null)
                    .commit()
            }
            NameofFragment.GROUP->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fcvMain, GroupFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
            }
            NameofFragment.STUDENT-> {
                //тут ещё что-то будет
            }
        }
        updateMenu(fragmentType)
    }

    override fun onDestroy(){
        MainRepository.getInstance().saveData()
        super.onDestroy()
    }

    override fun newTitle(_title: String) {
        title = _title
    }


}