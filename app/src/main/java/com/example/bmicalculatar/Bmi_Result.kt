package com.example.bmicalculatar

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.bmicalculatar.databinding.ActivityBmiResult2Binding

class Bmi_Result : AppCompatActivity() {
    lateinit var binding: ActivityBmiResult2Binding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBmiResult2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getStringExtra("count")

        binding.resultShow.text = "Your BMI\n$data"

        val udata = data.toString().toFloat()

        binding.bmichart.text = "underweight: <18.50\nnormal: 19-25\noverweight: 25-30"

        indexwisecondition(udata)


    }

    private fun indexwisecondition(result: Float) {

        when (result) {

            in 0.00..18.50 -> {

                binding.userCondition.text = "your underweight"

            }

            in 18.50..24.50 -> {

                binding.userCondition.text = "your Normalweight"

            }

            in 25.00..29.00 -> {

                binding.userCondition.text = "your Overweight"

            }


            else -> {

                binding.userCondition.text = "tui beshe den tikbena"
            }

        }

    }
}