package com.example.bai3buoi12

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FileAdapter(private val fileList: List<String>, private val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<FileAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_file, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fileName = fileList[position]
        holder.bind(fileName)
        holder.itemView.setOnClickListener { onItemClick(fileName) }
    }

    override fun getItemCount(): Int {
        return fileList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val fileNameTextView: TextView = itemView as TextView

        fun bind(fileName: String) {
            fileNameTextView.text = fileName
        }
    }
}