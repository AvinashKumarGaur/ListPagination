package com.example.demopagination.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demopagination.OnRepositoryClickListener
import com.example.demopagination.R
import com.example.demopagination.model.Items


class RepositoryAdapter(var repositories: List<Items>, private val listener: OnRepositoryClickListener) :
    RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val repository = repositories[position]
                    listener.onRepositoryClick(repository)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repository_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = repositories[position]
        holder.nameTextView.text = repository.name
        holder.descriptionTextView.text = repository.url
    }

    override fun getItemCount(): Int = repositories.size
}
