package com.example.lr2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

private const val EXTRA_ANSWER_IS_TRUE = "com.example.lr2.answer_is_true"
const val EXTRA_ANSWER_SHOW = "com.example.lr2.answer_show"

class CheatActivity : AppCompatActivity() {

    private var answerIsTrue = false
    private lateinit var showAnswerButton: Button

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cheat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        answerIsTrue=intent?.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)?:false
        showAnswerButton = findViewById(R.id.buttonCheat)
        showAnswerButton.setOnClickListener {
            val answerTextView = findViewById<TextView>(R.id.textViewInfo)
            val answerText = when {
                answerIsTrue -> R.string.true_text
                else -> R.string.false_text
            }
            answerTextView.setText(answerText)
            showAnswerButton.visibility = View.GONE
        }
    }
}