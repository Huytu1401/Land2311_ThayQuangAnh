package com.example.bai2_docfile_assets

import android.content.res.AssetManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bai2_docfile_assets.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    var _binding: ActivityMainBinding? = null
    val Bindding: ActivityMainBinding by lazy {
        requireNotNull(_binding)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(Bindding.root)
        Bindding.apply {
            btnLoad.setOnClickListener {
            edtAcount.setText(readFile())
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    fun readFile():String {
        val assetManager: AssetManager = applicationContext.assets
        val stringBuilder = StringBuilder()
        try {
            val bufferedReader = BufferedReader(InputStreamReader(assetManager.open("account.txt")))
            var line: String? = bufferedReader.readLine()
            while (line != null) {
                stringBuilder.append(line).append("\n")
                line = bufferedReader.readLine()
            }
            bufferedReader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }
    }
