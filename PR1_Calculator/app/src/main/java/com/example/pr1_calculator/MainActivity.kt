//package com.example.pr1_calculator
//
//import android.os.Bundle
//import androidx.activity.enableEdgeToEdge
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat
//
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//    }
//}

package com.example.pr1_calculator


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val curNum = findViewById<EditText>(R.id.textViewO)
        val backNum = findViewById<TextView>(R.id.textViewNum)
        val operationText = findViewById<TextView>(R.id.textViewSign)
        var num1 = 0.0
        var num2: Double

        val btn1: Button = findViewById(R.id.button1)
        btn1.setOnClickListener {
            curNum.append("1")
        }

        val btn2: Button = findViewById(R.id.button2)
        btn2.setOnClickListener {
            curNum.append("2")
        }

        val btn3: Button = findViewById(R.id.button3)
        btn3.setOnClickListener {
            curNum.append("3")
        }

        val btn4: Button = findViewById(R.id.button4)
        btn4.setOnClickListener {
            curNum.append("4")
        }

        val btn5: Button = findViewById(R.id.button5)
        btn5.setOnClickListener {
            curNum.append("5")
        }

        val btn6: Button = findViewById(R.id.button6)
        btn6.setOnClickListener {
            curNum.append("6")
        }

        val btn7: Button = findViewById(R.id.button7)
        btn7.setOnClickListener {
            curNum.append("7")
        }

        val btn8: Button = findViewById(R.id.button8)
        btn8.setOnClickListener {
            curNum.append("8")
        }

        val btn9: Button = findViewById(R.id.button9)
        btn9.setOnClickListener {
            curNum.append("9")
        }

        val btn0: Button = findViewById(R.id.button0)
        btn0.setOnClickListener {
            curNum.append("0")
        }

        val btnDot: Button = findViewById(R.id.buttonDot)
        btnDot.setOnClickListener {
            if(curNum.text.isEmpty())
                curNum.append("0.")
            else
                curNum.append(".")
        }

        val btnCL: Button = findViewById(R.id.buttonC)
        btnCL.setOnClickListener {
            curNum.text.clear()
            backNum.text = ""
            operationText.text = ""
        }


        var operation = ""

        val btnAdd: Button = findViewById(R.id.buttonPlus)
        btnAdd.setOnClickListener {
            if (backNum.text.isEmpty() && operationText.text.isEmpty()) {
                operation = "+"
                operationText.text = operation
                backNum.text = curNum.text
                curNum.text.clear()
                num1 = backNum.text.toString().toDouble()

            }
            else if(backNum.text.isNotEmpty() && operationText.text.isNotEmpty() && curNum.text.isNotEmpty()){
                num2 = curNum.text.toString().toDouble()
                num1 = backNum.text.toString().toDouble()
                var result = 0.0
                when (operation) {
                    "+" -> result = num1 + num2
                    "-" -> result = num1 - num2
                    "*" -> result = num1 * num2
                    "/" -> result = num1 / num2
                }
                if (result % 1.0 == 0.0) {
                    backNum.text = ""
                    backNum.append(result.roundToInt().toString())
                } else {
                    backNum.text = ""
                    backNum.append(result.toString())
                }
                num2 = result
                curNum.text.clear()
                operation = "+"
                operationText.text = operation
            }
            else if (backNum.text.isNotEmpty() && operationText.text.isNotEmpty() && curNum.text.isEmpty()){
                operation = "+"
                operationText.text = operation
            }

        }

        val btnMin: Button = findViewById(R.id.buttonMinus)
        btnMin.setOnClickListener {
            if (backNum.text.isEmpty() && operationText.text.isEmpty()) {
                operation = "-"
                operationText.text = operation
                backNum.text = curNum.text
                curNum.text.clear()
                num1 = backNum.text.toString().toDouble()

            }
            else if(backNum.text.isNotEmpty() && operationText.text.isNotEmpty() && curNum.text.isNotEmpty()){
                num2 = curNum.text.toString().toDouble()
                num1 = backNum.text.toString().toDouble()
                var result = 0.0
                when (operation) {
                    "+" -> result = num1 + num2
                    "-" -> result = num1 - num2
                    "*" -> result = num1 * num2
                    "/" -> result = num1 / num2
                }
                if (result % 1.0 == 0.0) {
                    backNum.text = ""
                    backNum.append(result.roundToInt().toString())
                } else {
                    backNum.text = ""
                    backNum.append(result.toString())
                }
                num2 = result
                curNum.text.clear()
                operation = "-"
                operationText.text = operation
            }
            else if (backNum.text.isNotEmpty() && operationText.text.isNotEmpty() && curNum.text.isEmpty()){
                operation = "-"
                operationText.text = operation
            }
        }

        val btnDiv: Button = findViewById(R.id.buttonDiv)
        btnDiv.setOnClickListener {
            if (backNum.text.isEmpty() && operationText.text.isEmpty()) {
                operation = "/"
                operationText.text = operation
                backNum.text = curNum.text
                curNum.text.clear()
                num1 = backNum.text.toString().toDouble()

            }
            else if(backNum.text.isNotEmpty() && operationText.text.isNotEmpty() && curNum.text.isNotEmpty()){
                num2 = curNum.text.toString().toDouble()
                num1 = backNum.text.toString().toDouble()
                var result = 0.0
                when (operation) {
                    "+" -> result = num1 + num2
                    "-" -> result = num1 - num2
                    "*" -> result = num1 * num2
                    "/" -> result = num1 / num2
                }
                if (result % 1.0 == 0.0) {
                    backNum.text = ""
                    backNum.append(result.roundToInt().toString())
                } else {
                    backNum.text = ""
                    backNum.append(result.toString())
                }
                num2 = result
                curNum.text.clear()
                operation = "/"
                operationText.text = operation
            }
            else if (backNum.text.isNotEmpty() && operationText.text.isNotEmpty() && curNum.text.isEmpty()){
                operation = "/"
                operationText.text = operation
            }
        }

        val btnMulti: Button = findViewById(R.id.buttonMult)
        btnMulti.setOnClickListener {
            if (backNum.text.isEmpty() && operationText.text.isEmpty()) {
                operation = "*"
                operationText.text = operation
                backNum.text = curNum.text
                curNum.text.clear()
                num1 = backNum.text.toString().toDouble()

            }
            else if(backNum.text.isNotEmpty() && operationText.text.isNotEmpty() && curNum.text.isNotEmpty()){
                num2 = curNum.text.toString().toDouble()
                num1 = backNum.text.toString().toDouble()
                var result = 0.0
                when (operation) {
                    "+" -> result = num1 + num2
                    "-" -> result = num1 - num2
                    "*" -> result = num1 * num2
                    "/" -> result = num1 / num2
                }
                if (result % 1.0 == 0.0) {
                    backNum.text = ""
                    backNum.append(result.roundToInt().toString())
                } else {
                    backNum.text = ""
                    backNum.append(result.toString())
                }
                num2 = result
                curNum.text.clear()
                operation = "*"
                operationText.text = operation
            }
            else if (backNum.text.isNotEmpty() && operationText.text.isNotEmpty() && curNum.text.isEmpty()){
                operation = "*"
                operationText.text = operation
            }
        }


        val btnEq: Button = findViewById(R.id.buttonEquals)
        btnEq.setOnClickListener {
            try {
                num2 = curNum.text.toString().toDouble()
                num1 = backNum.text.toString().toDouble()
                var result = 0.0
                if (operation == "/" && num2 == 0.0) {

                    curNum.error = "Деление на ноль не допускается"
                    curNum.requestFocus()
                    return@setOnClickListener
                }
                else {
                    when (operation) {
                        "+" -> result = num1 + num2
                        "-" -> result = num1 - num2
                        "*" -> result = num1 * num2
                        "/" -> result = num1 / num2
                    }
                    if (result % 1.0 == 0.0) {
                        curNum.text.clear()
                        curNum.append(result.roundToInt().toString())
                    } else {
                        curNum.text.clear()
                        curNum.append(result.toString())
                    }
                    //curNum.text.clear()
                    backNum.text = ""
                    operationText.text = ""
                    num2 = result

                }
            }
            catch (e : Exception){
                if (curNum.text.isEmpty()) {

                    //curNum.text.clear()
                    curNum.error = "Введите число"
                    curNum.requestFocus()
                    return@setOnClickListener

                } else if (backNum.text.isEmpty()) {

                    //curNum.text.clear()
                    curNum.error = "Введите операцию"
                    curNum.requestFocus()
                    return@setOnClickListener
                } else if (operationText.text.isEmpty()) {

                    //curNum.text.clear()
                    curNum.error = "Введите операцию"
                    curNum.requestFocus()
                    return@setOnClickListener
                }
            }

        }
    }
}