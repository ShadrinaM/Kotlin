package com.example.lr3

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lr3.data.NameofFragment
import com.example.lr3.fragments.FacultyFragment
import com.example.lr3.repositores.MainRepository
import kotlin.jvm.internal.MagicApiIntrinsics

class MainActivity : AppCompatActivity() {

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        _miNewFaculty = menu?.findItem(R.id.miNewFaculty)
        _miChangeFaculty = menu?.findItem(R.id.miChangeFaculty)
        _miDeleteFaculty = menu?.findItem(R.id.miDeleteFaculty)
        return true
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

            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showFragment(fragmentType: NameofFragment){
        when (fragmentType){
            NameofFragment.FACULTY->{
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fcvMain, FacultyFragment.getInstance())
                    .addToBackStack(null)
                    .commit()
            }
            NameofFragment.GROUP->{

            }
            NameofFragment.STUDENT->{

            }
        }
    }

    override fun onDestroy() {
        MainRepository.getInstance().saveData()
        super.onDestroy()

    }
}