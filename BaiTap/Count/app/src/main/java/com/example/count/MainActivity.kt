package com.example.count

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.count.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding by lazy {
        requireNotNull(_binding)
    }
    private var mValue = 0
    private lateinit var sharedPreferences: SharedPreferences
    private var selectedColor: Int = Color.WHITE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)
        mValue = sharedPreferences.getInt(COUNT_KEY, 0)
        selectedColor = sharedPreferences.getInt(COLOR_KEY, Color.WHITE)

        updateTextView()
        binding.tvCount.setBackgroundColor(selectedColor)
        binding.apply {

            btnCount.setOnClickListener {
                incrementCount()
            }
            btnReset.setOnClickListener {
                reset()
            }
            btnBlack.setOnClickListener {
                setColor(Color.BLACK)

            }
            btnRed.setOnClickListener {
                setColor(Color.RED)
            }
            btnBlue.setOnClickListener {
                setColor(Color.BLUE)
            }
            btnGreen.setOnClickListener {
                setColor(Color.GREEN)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setColor(color: Int) {
        selectedColor = color
        binding.tvCount.setBackgroundColor(selectedColor)
        savePreferences()
    }

    private fun incrementCount() {
        mValue++
        updateTextView()
        savePreferences()
    }

    private fun reset() {
        mValue = 0
        selectedColor = Color.WHITE
        updateTextView()
        binding.tvCount.setBackgroundColor(selectedColor)
        savePreferences()
    }

    private fun updateTextView() {
        binding.tvCount.text = mValue.toString()
    }

    private fun savePreferences() {
        val editor = sharedPreferences.edit()
        editor.putInt(COUNT_KEY, mValue)
        editor.putInt(COLOR_KEY, selectedColor)
        editor.apply()
    }

    companion object {
        const val COUNT_KEY = "count"
        const val COLOR_KEY = "color"
    }
}