package com.example.pr2_form

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var fiotv : TextView
    private lateinit var grouptv : TextView
    private lateinit var facultytv : TextView
    private lateinit var dateBtv : TextView
    private lateinit var phonetv : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        fiotv = findViewById(R.id.tvfio)
        grouptv = findViewById(R.id.tvgroup)
        facultytv = findViewById(R.id.tvfaculty)
        dateBtv = findViewById(R.id.tvdateB)
        phonetv = findViewById(R.id.tvphone)


        findViewById<Button>(R.id.btchange).setOnClickListener {
            val fillfiotv = if (fiotv.text.toString() == "ФИО") "" else fiotv.text.toString()
            val fillgrouptv = if (grouptv.text.toString() == "Группа") "" else grouptv.text.toString()
            val fillfacultytv = if (facultytv.text.toString() == "Факультет") "" else facultytv.text.toString()
            val filldateBtv = if (dateBtv.text.toString() == "**.**.****") "" else dateBtv.text.toString()
            val fillphonetv = if (phonetv.text.toString() == "+7(***)*******") "" else phonetv.text.toString()

            val intent = FillActivity.newIntent(this@MainActivity, fillfiotv, fillgrouptv, fillfacultytv, filldateBtv, fillphonetv)
            resultLauncher.launch(intent)
        }
    }

    // обработчик для получения результата из другой активности
    // ActivityResultContracts.StartActivityForResult() — хотим запустить активность и получить результат
    // result -> когда активность завершится и вернет результат будет вызвано следующее
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            val data: Intent? = result.data
            // заполняет поля данными из Intent
            fiotv.text = data?.getStringExtra(EXTRA_FIO)?:""
            grouptv.text = data?.getStringExtra(EXTRA_GROUP)?:""
            facultytv.text=data?.getStringExtra(EXTRA_FACULTY)?:""
            dateBtv.text=data?.getStringExtra(EXTRA_DATE_OF_BIRTH)?:""
            phonetv.text=data?.getStringExtra(EXTRA_PHONE)?:""
        }
    }
}