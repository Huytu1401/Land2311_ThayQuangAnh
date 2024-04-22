package com.example.bai3buoi12

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bai3buoi12.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    var _binding: ActivityMainBinding? = null
    val binding: ActivityMainBinding by lazy {
        requireNotNull(_binding)
    }
    private lateinit var fileAdapter: FileAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rcvFile.layoutManager = LinearLayoutManager(this)
        fileAdapter = FileAdapter(getFileList(), this::onItemClick)
        binding.rcvFile.adapter = fileAdapter

        binding.btnSave.setOnClickListener {
            saveToFile()
            fileAdapter = FileAdapter(getFileList(), this::onItemClick)
            binding.rcvFile.adapter = fileAdapter
            fileAdapter.notifyDataSetChanged()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun saveToFile() {
        val text = binding.edtContent.text.toString()
        if (text.isNotEmpty()) {
            val fileName = "file_${binding.edtNameFile.text.trim()}.txt"
            val file = File(getExternalFilesDir(null), fileName)
            file.writeText(text)
            binding.edtContent.text.clear()
            fileAdapter.notifyDataSetChanged()

        } else {
            Toast.makeText(this, "Vui lòng nhập văn bản", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getFileList(): List<String> {
        val directory = getExternalFilesDir(null)
        return directory?.listFiles()?.map { it.name } ?: emptyList()
    }

    private fun onItemClick(fileName: String) {
        val file = File(getExternalFilesDir(null), fileName)
        val fileContent = file.readText()

        Toast.makeText(this, fileContent, Toast.LENGTH_SHORT).show()
    }
}