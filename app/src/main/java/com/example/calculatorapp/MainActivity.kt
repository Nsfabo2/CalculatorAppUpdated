package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

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

    //var displayText = "0"

    //numbers
    private lateinit var Zero: Button
    private lateinit var One: Button
    private lateinit var Two: Button
    private lateinit var Three: Button
    private lateinit var Four: Button
    private lateinit var Five: Button
    private lateinit var Six: Button
    private lateinit var Seven: Button
    private lateinit var Eight: Button
    private lateinit var Nine: Button

    //operations
    private lateinit var PlusMinus: Button
    private lateinit var Decimal: Button
    private lateinit var Multiplication: Button
    private lateinit var Dividtion: Button
    private lateinit var Addtion: Button
    private lateinit var Subtraction: Button
    private lateinit var ClearResults: Button
    private lateinit var Result: Button
    private lateinit var Delete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //others initializing
        display = findViewById(R.id.DisplayResualtTV)
        display.hint = "0"
        //numbers initializing
        Zero = findViewById(R.id.btn0)
        Zero.setOnClickListener { SetNumber('0') }
        One = findViewById(R.id.btn1)
        One.setOnClickListener { SetNumber('1') }
        Two = findViewById(R.id.btn2)
        Two.setOnClickListener { SetNumber('2') }
        Three = findViewById(R.id.btn3)
        Three.setOnClickListener { SetNumber('3') }
        Four = findViewById(R.id.btn4)
        Four.setOnClickListener { SetNumber('4') }
        Five = findViewById(R.id.btn5)
        Five.setOnClickListener { SetNumber('5') }
        Six = findViewById(R.id.btn6)
        Six.setOnClickListener { SetNumber('6') }
        Seven = findViewById(R.id.btn7)
        Seven.setOnClickListener { SetNumber('7') }
        Eight = findViewById(R.id.btn8)
        Eight.setOnClickListener { SetNumber('8') }
        Nine = findViewById(R.id.btn9)
        Nine.setOnClickListener { SetNumber('9') }

        //operations initializing
        Addtion = findViewById(R.id.Plusbtn)
        Addtion.setOnClickListener { SetOperator('+') }
        Subtraction = findViewById(R.id.Minusbtn)
        Subtraction.setOnClickListener { SetOperator('-') }
        Multiplication = findViewById(R.id.Multiplybtn)
        Multiplication.setOnClickListener { SetOperator('*') }
        Dividtion = findViewById(R.id.Dividebtn)
        Dividtion.setOnClickListener { SetOperator('/') }
        Decimal = findViewById(R.id.Decimalbtn)
        Decimal.setOnClickListener { DecimalClicked() }
        PlusMinus = findViewById(R.id.NegativePositivebtn)
        PlusMinus.setOnClickListener { PlusMinusClicked() }
        ClearResults = findViewById(R.id.Clearbtn)
        ClearResults.setOnClickListener { ClearResualts() }
        Result = findViewById(R.id.Equalbtn)
        Result.setOnClickListener { Calculate() }
        Delete = findViewById(R.id.Deletebtn)
        Delete.setOnClickListener { Delete() }

        title = "Calculator"
    }//end oncreate

    fun SetNumber(num: Char){
        if(operator==' '){
            num1 += num
            display.text = num1
        }else{
            num2 += num
            val text = num1 + operator + num2
            display.text = text
        }
    }//end setNum

    fun SetOperator(op: Char){
        operator = op
        val text = num1 + operator
        display.text = text
    }//end handelOperator

    fun DecimalClicked(){
        if(operator==' '&&!num1.contains(".")){SetNumber('.')}
        if(operator!=' '&&!num2.contains(".")){SetNumber('.')}
    }//end onClickDecimal

    fun PlusMinusClicked(){
        if(operator==' '){
            num1 = if(num1.startsWith("-")){
                num1.substring(1, num1.length)
            } else{
                "-$num1"
            }
            display.text = num1
        }else{
            num2 = if(num2.startsWith("-")){
                num2.substring(1, num2.length)
            } else{
                "-$num2"
            }
            val text = num1 + operator + num2
            display.text = text
        }
    }//end onClickPlusMinus

    fun ClearResualts(){
        output = 0f
        operator = ' '
        num1 = ""
        num2 = ""
        display.text = "0"
    }//end clearAll

    fun Calculate(){
        var divByZero = false
        when (operator) {
            '+' -> output = num1.toFloat() + num2.toFloat()
            '-' -> output = num1.toFloat() - num2.toFloat()
            '*' -> output = num1.toFloat() * num2.toFloat()
            '/' -> if(num1.toFloat()!=0f&&num2.toFloat()!=0f){
                output = num1.toFloat() / num2.toFloat()
            }else{
                divByZero = true
                //Snackbar.make(clo, "You cant divide by zero!", Snackbar.LENGTH_LONG).show()

            }
        }
        num1 = output.toString()
        num2 = ""
        display.text = output.toString()
        if(divByZero){
            ClearResualts()
        }
    }//end calculate

    fun Delete(){
        if(operator==' '){
            if(num1.isNotEmpty()){
                num1 = num1.substring(0, num1.length - 1)
                if(num1.isEmpty()){display.text = "0"}
                else{
                    display.text = num1
                }
            }
        }else{
            if(num2.isNotEmpty()){
                num2 = num2.substring(0, num2.length - 1)
                val text = num1 + operator + num2
                display.text = text
            }else{
                operator=' '
                display.text = num1
            }
        }
    }//end deleteLast

}//end class