package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextWatcher
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.calculateButton.setOnClickListener{ calculateTip() }


        binding.costOfService.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun afterTextChanged(s: android.text.Editable?) {
                calculateTip()
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.tipResult.text = ""
            }
        })

        binding.tipOptions.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.option_twenty_percent -> binding.tipOptions.check(R.id.option_twenty_percent)
                R.id.option_eighteen_percent -> binding.tipOptions.check(R.id.option_eighteen_percent)
                R.id.option_fifteen_percent -> binding.tipOptions.check(R.id.option_fifteen_percent)
            }
            calculateTip()
        }

        binding.roundUpSwitch.setOnCheckedChangeListener { _, isChecked ->
            calculateTip()
        }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        if (stringInTextField.isNotEmpty()) {
            val cost = stringInTextField.toDouble()
            val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
                R.id.option_twenty_percent -> 0.20
                R.id.option_eighteen_percent -> 0.18
                else -> 0.15
            }
            var tip = tipPercentage * cost
            val roundUp = binding.roundUpSwitch.isChecked
            if (roundUp) {
                tip = kotlin.math.ceil(tip)
            }
            NumberFormat.getCurrencyInstance().format(tip)
            val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
            binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
        }
        else {
            binding.tipResult.text = ""
        }
    }

}
