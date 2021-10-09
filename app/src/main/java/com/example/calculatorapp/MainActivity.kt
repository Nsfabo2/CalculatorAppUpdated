package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

/*
Build a single activity calculator application

The calculator should be able to handle addition, subtraction, multiplication, and division of two numbers

Include a clear button to reset the calculator

Include a +/- button to allow for negative numbers

Include a decimal button

Make sure the user cannot divide by zero
 */

class MainActivity : AppCompatActivity() {

    //others
    private lateinit var display: TextView
    var output = 0f
    var operator = ' '
    var num1 = ""
    var num2 = ""

    var displayText = "0"

    //numbers
    private lateinit var zero: Button
    private lateinit var one: Button
    private lateinit var two: Button
    private lateinit var three: Button
    private lateinit var four: Button
    private lateinit var five: Button
    private lateinit var six: Button
    private lateinit var seven: Button
    private lateinit var eight: Button
    private lateinit var nine: Button

    //operations
    private lateinit var plusMinus: Button
    private lateinit var decimal: Button
    private lateinit var multiply: Button
    private lateinit var divide: Button
    private lateinit var add: Button
    private lateinit var subtract: Button
    private lateinit var clear: Button
    private lateinit var result: Button
    private lateinit var del: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //others initializing
        display = findViewById(R.id.tvDisplay)

        //numbers initializing
        zero = findViewById(R.id.bt0)
        zero.setOnClickListener { setNum('0') }
        one = findViewById(R.id.bt1)
        one.setOnClickListener { setNum('1') }
        two = findViewById(R.id.bt2)
        two.setOnClickListener { setNum('2') }
        three = findViewById(R.id.bt3)
        three.setOnClickListener { setNum('3') }
        four = findViewById(R.id.bt4)
        four.setOnClickListener { setNum('4') }
        five = findViewById(R.id.bt5)
        five.setOnClickListener { setNum('5') }
        six = findViewById(R.id.bt6)
        six.setOnClickListener { setNum('6') }
        seven = findViewById(R.id.bt7)
        seven.setOnClickListener { setNum('7') }
        eight = findViewById(R.id.bt8)
        eight.setOnClickListener { setNum('8') }
        nine = findViewById(R.id.bt9)
        nine.setOnClickListener { setNum('9') }

        //operations initializing
        add = findViewById(R.id.btPlus)
        add.setOnClickListener { handleOperator('+') }
        subtract = findViewById(R.id.btMinus)
        subtract.setOnClickListener { handleOperator('-') }
        multiply = findViewById(R.id.btMultiply)
        multiply.setOnClickListener { handleOperator('*') }
        divide = findViewById(R.id.btDiv)
        divide.setOnClickListener { handleOperator('/') }
        decimal = findViewById(R.id.btDecimal)
        decimal.setOnClickListener { onClickDecimal() }
        plusMinus = findViewById(R.id.btNegative)
        plusMinus.setOnClickListener { onClickPlusMinus() }
        clear = findViewById(R.id.btClear)
        clear.setOnClickListener { clearAll() }
        result = findViewById(R.id.btEquals)
        result.setOnClickListener { calculate() }
        del = findViewById(R.id.btDel)
        del.setOnClickListener { deleteLast() }

        title = "Calculator"
    }//end oncreate

    fun setNum(num: Char){
        if(operator==' '){
            num1 += num
            displayText = num1
        }else{
            num2 += num
            val text = num1 + operator + num2
            displayText = text
        }
    }//end setNum

    fun handleOperator(op: Char){
        operator = op
        val text = num1 + operator
        displayText = text
    }//end handelOperator

    fun onClickDecimal(){
        if(operator==' '&&!num1.contains(".")){setNum('.')}
        if(operator!=' '&&!num2.contains(".")){setNum('.')}
    }//end onClickDecimal

    fun onClickPlusMinus(){
        if(operator==' '){
            num1 = if(num1.startsWith("-")){
                num1.substring(1, num1.length)
            } else{
                "-$num1"
            }
            displayText = num1
        }else{
            num2 = if(num2.startsWith("-")){
                num2.substring(1, num2.length)
            } else{
                "-$num2"
            }
            val text = num1 + operator + num2
            displayText = text
        }
    }//end onClickPlusMinus

    fun clearAll(){
        output = 0f
        operator = ' '
        num1 = ""
        num2 = ""
        displayText = "0"
    }//end clearAll

    fun calculate(){
        var divByZero = false
        when (operator) {
            '+' -> output = num1.toFloat() + num2.toFloat()
            '-' -> output = num1.toFloat() - num2.toFloat()
            '*' -> output = num1.toFloat() * num2.toFloat()
            '/' -> if(num1.toFloat()!=0f&&num2.toFloat()!=0f){
                output = num1.toFloat() / num2.toFloat()
            }else{
                divByZero = true
            }
        }
        num1 = output.toString()
        num2 = ""
        displayText = output.toString()
        if(divByZero){clearAll()}
    }//end calculate

    fun deleteLast(){
        if(operator==' '){
            if(num1.isNotEmpty()){
                num1 = num1.substring(0, num1.length - 1)
                if(num1.isEmpty()){displayText = "0"}
                else{displayText = num1}
            }
        }else{
            if(num2.isNotEmpty()){
                num2 = num2.substring(0, num2.length - 1)
                val text = num1 + operator + num2
                displayText = text
            }else{
                operator=' '
                displayText = num1
            }
        }
    }//end deleteLast

}//end class