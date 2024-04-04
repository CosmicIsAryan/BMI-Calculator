package com.example.bmicalculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View.OnFocusChangeListener
import android.view.View.OnTouchListener
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.bmicalculator.databinding.ActivityBmiBinding


class BmiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBmiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        hint(binding.weightInput)
        hint(binding.heightInput)
        hint(binding.heightFeet)
        hint(binding.heightInch)


        binding.radioGroup.setOnCheckedChangeListener{ group , checkedId ->

            if (checkedId == R.id.Cm){
                binding.heightFeet.isVisible =false
                binding.heightFeet.isClickable=false
                binding.heightInch.isVisible =false
                binding.heightInch.isClickable=false

                binding.heightInput.isClickable=true
                binding.heightInput.isVisible =true


                binding.calculate.setOnClickListener {

                    if (checkInput(binding.heightInput, binding.weightInput)) {
                        val _weight = binding.weightInput.text.toString()
                        val _height = binding.heightInput.text.toString()


                        val weight = _weight.toFloat()
                        val height = (_height.toFloat() / 100)
                        val bmi = calBMI(weight, height)


                        val intent = Intent(this@BmiActivity, ResultActivity::class.java)

                        intent.putExtra("BMI", bmi.toString())
                        startActivity(intent)
                        finish()


                    } else {
                        binding.error.isVisible = true
                        binding.error.text = "Misssing Field"
                    }
                }





            }else{
                binding.heightFeet.isVisible =true
                binding.heightFeet.isClickable=true
                binding.heightInch.isVisible =true
                binding.heightInch.isClickable=true

                binding.heightInput.isClickable=false
                binding.heightInput.isVisible =false

                binding.calculate.setOnClickListener {

                    if (checkInput(binding.heightFeet, binding.weightInput)) {
                        val _inches = binding.heightInch.text.toString()
                        val feets = binding.heightFeet.text.toString()
                        val meters = (feets.toFloat() + (_inches.toFloat() / 12))* 0.3048
                        val _weight = binding.weightInput.text.toString()
                        val weight = _weight.toFloat()
                        val bmi = calBMI(weight , meters.toFloat())

                        val intent = Intent(this@BmiActivity, ResultActivity::class.java)

                        intent.putExtra("BMI" , bmi.toString())
                        startActivity(intent)
                        finish()


                    } else {
                        binding.error.isVisible = true
                        binding.error.text = "Misssing Field"
                    }
                }




            }
        }





        }




    @SuppressLint("ClickableViewAccessibility")
    private fun hint(editText :EditText){
        editText.setOnTouchListener(OnTouchListener { v, event ->
            editText.setHint("")
            binding.error.isVisible=false

            false
        })

        editText.setOnFocusChangeListener(OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                editText.setHint("0")
            }
        })
    }

    private fun calBMI( weight : Float  , height: Float) : Float {

        var bmi = weight/(height*height)
        val bmi2Digits = "%.2f".format(bmi).toFloat()
        return bmi2Digits
    }

    private fun checkInput(weight :EditText , height:EditText) :Boolean {
        if(weight.text.isEmpty() || height.text.isEmpty() ){
            return false
        }
        return true
    }

}
