package com.example.bmi_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcButton = findViewById<Button>(R.id.btnCalculate)


        calcButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()

            if(validateInput(weight,height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digits)
            }

        }

    }

    //for displaying the result
    private fun displayResult(bmi:Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString()
        info.text = "Normal range is 18.5 - 24.9"

        var resultText = ""

        when{
            bmi < 18.5 ->{
                resultText = "Underweight"
            }
            bmi in 18.50..24.99 ->{
                resultText = "Healthy"
            }
            bmi in 25.00..29.99 ->{
                resultText = "Overweight"
            }
            bmi > 29.99 ->{
                resultText = "Obese"
            }
        }

        resultDescription.text = resultText


    }

    //to control weight or heigth whether null or not
    private fun validateInput(weight:String?,height:String?):Boolean{
        when{
            (weight.isNullOrEmpty() && height.isNullOrEmpty() ) ->{
                Toast.makeText(this,"Weight and height are empty", Toast.LENGTH_LONG).show()
                return false
            }

            weight.isNullOrEmpty() ->{
                Toast.makeText(this,"Weight is empty", Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() ->{
                Toast.makeText(this,"Height is empty", Toast.LENGTH_LONG).show()
                return false
            }
            else ->{
                return true
            }
        }

    }
}