package com.example.bmicalculatar

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.bmicalculatar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.switchToggle.setOnCheckedChangeListener { _, _ ->
            toggleMeasurementSystem()
        }

        binding.btnCalculate.setOnClickListener {
            calculateAndOpenResult()
        }
    }

    private fun calculateAndOpenResult() {
        val isMetric = binding.showSystem.text == "Metric System"
        val bmi: Float

        if (isMetric) {
            val weight = binding.etWeight.text.toString().toFloatOrNull()
            val height = binding.etHeight.text.toString().toFloatOrNull()

            if (weight == null || height == null || height <= 0f) {
                Toast.makeText(this, "Please enter valid weight and height", Toast.LENGTH_SHORT).show()
                return
            }

            bmi = weight / (height * height)
        } else {
            val weightLb = binding.etlb.text.toString().toFloatOrNull()
            val feet = binding.etFeet.text.toString().toFloatOrNull() ?: 0f
            val inches = binding.etInche.text.toString().toFloatOrNull() ?: 0f

            if (weightLb == null || weightLb <= 0f) {
                Toast.makeText(this, "Please enter valid weight in pounds", Toast.LENGTH_SHORT).show()
                return
            }

            val totalInches = (feet * 12) + inches
            if (totalInches <= 0f) {
                Toast.makeText(this, "Please enter valid height", Toast.LENGTH_SHORT).show()
                return
            }

            bmi = (weightLb * 703) / (totalInches * totalInches)
        }

        val bmiFormatted = "%.2f".format(bmi)
        val intent = Intent(this, Bmi_Result::class.java).apply {
            putExtra("count", bmiFormatted)
        }
        startActivity(intent)
    }

    private fun toggleMeasurementSystem() {
        if (binding.showSystem.text == "Metric System") {
            binding.showSystem.text = "Imperial System"
            binding.metric.visibility = View.GONE
            binding.imperial.visibility = View.VISIBLE
            binding.switchText.text = "Metric System"
        } else {
            binding.showSystem.text = "Metric System"
            binding.metric.visibility = View.VISIBLE
            binding.imperial.visibility = View.GONE
            binding.switchText.text = "Imperial System"
        }
    }
}
