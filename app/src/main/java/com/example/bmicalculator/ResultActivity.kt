package com.example.bmicalculator

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bmicalculator.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val intent =intent
        val BMI = intent.getStringExtra("BMI")
        binding.resultText.text = BMI.toString()
        if (BMI != null) {
            binding.categoryText.text = obesity(BMI.toFloat())
        }

        binding.backButton.setOnClickListener{
            val intent = Intent(this@ResultActivity, BmiActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.back.setOnClickListener{
            val intent = Intent(this@ResultActivity, BmiActivity::class.java)
            startActivity(intent)
            finish()
        }



     }

    private fun obesity(bmi:Float) : String{

        when(bmi){
            in 0.0..18.4 -> {
                binding.emoji.setImageResource(R.drawable.emoji1)
                return "Under Weight" }

            in 18.5 ..24.9 ->  {
                binding.emoji.setImageResource(R.drawable.emoji2)
                return "Normal"
            }
            in 25.0 .. 29.9 -> {
                binding.emoji.setImageResource(R.drawable.emoji3)
                return "OverWeight"
            }
            in 30.0 ..34.9 ->{
                binding.emoji.setImageResource(R.drawable.emoji4)
                return "Obese"}

            else ->{
                binding.emoji.setImageResource(R.drawable.emoji5)
                return "Extremly obese"
            }

        }

    }


}