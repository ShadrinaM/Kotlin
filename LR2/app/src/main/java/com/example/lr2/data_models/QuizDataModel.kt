package com.example.lr2.data_models

import androidx.lifecycle.ViewModel
import com.example.lr2.R
import com.example.lr2.data.Quest

class QuizDataModel: ViewModel() {

    private val questionBank = listOf(
        Quest(R.string.question_1_no, false),
        Quest(R.string.question_2_no, false),
        Quest(R.string.question_3_no, false),
        Quest(R.string.question_4_no, false),
        Quest(R.string.question_5_yes, true),
        Quest(R.string.question_6_yes, true),
        Quest(R.string.question_7_yes, true)
    )

    private var currentIndex=0;

    val currentQuestAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestResId: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrev(){
        currentIndex = (questionBank.size + currentIndex - 1) % questionBank.size
    }

}