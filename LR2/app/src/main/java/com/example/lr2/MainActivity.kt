package com.example.lr2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.lr2.data_models.QuizDataModel

class MainActivity : AppCompatActivity() {
    //lateinit - отложенная инициализация
    // к моменту первого использования объекта обещаем
    // что значение должно быть инициализировано
    private lateinit var ButtonTrue: Button
    private lateinit var ButtonFalse: Button
    private lateinit var imageButtonPrev: ImageButton
    private lateinit var imageButtonNext: ImageButton
    private lateinit var questionTextView: TextView



    //провайдер моделей, регистрируем класс и этот провайдер подключается
    // к соответсвующему вьюмодел, этот вью модел будет присвоен данной переменной

    //by lazy - инициализируется во время первого обращения к переменной
    private val quizViewModel: QuizDataModel by lazy{
        val provider = ViewModelProvider(this)
        provider.get(QuizDataModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        // R класс это все классы проекта
        ButtonTrue=findViewById(R.id.ButtonTrue)
        ButtonFalse=findViewById(R.id.ButtonFalse)
        imageButtonPrev=findViewById(R.id.imageButtonPrev)
        imageButtonNext=findViewById(R.id.imageButtonNext)
        questionTextView=findViewById(R.id.textViewQuest)

        updateQuestion()

        ButtonTrue.setOnClickListener{
            checkAnswer(true)
        }
        ButtonFalse.setOnClickListener{
            checkAnswer(false)
        }
        imageButtonNext.setOnClickListener{
            quizViewModel.moveToNext()
            updateQuestion()
        }
        imageButtonPrev.setOnClickListener{
            quizViewModel.moveToPrev()
            updateQuestion()
        }

        findViewById<Button>(R.id.buttonShowCheat).setOnClickListener {
            //или код  фотки
            val intent = CheatActivity.newIntent(this@MainActivity, quizViewModel.currentQuestAnswer)
            startActivity(intent)
        }

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                quizViewModel.isCheater = data?. getBooleanExtra(EXTRA_ANSWER_SHOW, false)?:false
            }

        }

    }

    private fun updateQuestion(){
        //получаем id вопроса
        //по id сетер setText
        val questionTextResId = quizViewModel.currentQuestResId
        questionTextView.setText(questionTextResId)

    }

    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer = quizViewModel.currentQuestAnswer
        val messageResId = when {
            userAnswer == correctAnswer -> R.string.true_text
            else -> R.string.false_text
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
            .show()

        // всплывающее сообщение(контекст, строка или id строкового ресурса,время отображения сообщения)
    }
}