package com.example.pr2_form

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// доступны в любом классе, функции или файле внутри модуля com.example.pr2_form
// для другого модуля они будут не доступны
const val EXTRA_FIO = "com.example.pr2_form.fio"
const val EXTRA_GROUP = "com.example.pr2_form.group"
const val EXTRA_FACULTY = "com.example.pr2_form.faculty"
const val EXTRA_DATE_OF_BIRTH = "com.example.pr2_form.date_of_birth"
const val EXTRA_PHONE = "com.example.pr2_form.phone"

class FillActivity : AppCompatActivity() {

    // lateinit - инит перемен без немедленного присвоения значения,
    // но с гарантией, что значение будет присвоено до первого использования
    private lateinit var fio : EditText
    private lateinit var group : EditText
    private lateinit var faculty : EditText
    private lateinit var dateB : EditText
    private lateinit var phone : EditText
    private lateinit var savebt : Button

    // типо как статический метод (можно вызвать без создания экземпляра класса)
    companion object{
        // создает заполняет данными объект Intent для запуска FillActivity
        // newIntent (контекст из которого запускается активность, данные, которые нужно передать в FillActivity)
        fun newIntent(packageContext: Context, fio: String, group: String, faculty: String, dateB: String, phone: String): Intent {
            // apply - выполняет блок кода, переданный в лямбде, в контексте этого объекта
            // и возвращает тот же объект после настройки
            return Intent(packageContext, FillActivity::class.java).apply {
                // putExtra - добавляет дополнительные данные (extras) в Intent для передачи в другую активность
                putExtra(EXTRA_FIO, fio)
                putExtra(EXTRA_GROUP, group)
                putExtra(EXTRA_FACULTY, faculty)
                putExtra(EXTRA_DATE_OF_BIRTH, dateB)
                putExtra(EXTRA_PHONE, phone)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fill)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ссылки на поля
        fio = findViewById(R.id.etfio)
        group = findViewById(R.id.etgroup)
        faculty = findViewById(R.id.etfaculty)
        dateB = findViewById(R.id.etdateB)
        phone = findViewById(R.id.etphone)
        savebt = findViewById(R.id.btsave)

        // заполнение полей с помощью extras
        fio.setText(intent?.getStringExtra(EXTRA_FIO)?:"")
        group.setText(intent?.getStringExtra(EXTRA_GROUP)?:"")
        faculty.setText(intent?.getStringExtra(EXTRA_FACULTY)?:"")
        dateB.setText(intent?.getStringExtra(EXTRA_DATE_OF_BIRTH)?:"")
        phone.setText(intent?.getStringExtra(EXTRA_PHONE)?:"")

        savebt.setOnClickListener{
            // apply - выполняет блок кода, переданный в лямбде, в контексте этого объекта
            // и возвращает тот же объект после настройки
            val data = Intent().apply{
                // putExtra - добавляет дополнительные данные (extras) в Intent для передачи в другую активность
                putExtra(EXTRA_FIO, fio.text.toString())
                putExtra(EXTRA_GROUP, group.text.toString())
                putExtra(EXTRA_FACULTY, faculty.text.toString())
                putExtra(EXTRA_DATE_OF_BIRTH, dateB.text.toString())
                putExtra(EXTRA_PHONE, phone.text.toString())
            }
            // устанавливает результат активности как успешный (RESULT_OK) и передает данные обратно в вызывающую активность
            setResult(Activity.RESULT_OK, data)
            // завершает текущую активность и возвращает управление вызывающей активности
            finish()
        }
    }
}