package com.sych.mornhousetest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class FactAdapter(private val onItemClick: (FactEntity) -> Unit) :
    ListAdapter<FactEntity, FactAdapter.FactViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fact, parent, false)
        return FactViewHolder(view)
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        val fact = getItem(position)
        holder.bind(fact)
    }

    inner class FactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val numberTextView: TextView = itemView.findViewById(R.id.numberTextView)
        private val previewTextView: TextView = itemView.findViewById(R.id.previewTextView)

        fun bind(fact: FactEntity) {
            numberTextView.text = fact.number
            previewTextView.text = fact.fact.take(50)
            itemView.setOnClickListener {
                onItemClick(fact)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<FactEntity>() {
        override fun areItemsTheSame(oldItem: FactEntity, newItem: FactEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FactEntity, newItem: FactEntity): Boolean {
            return oldItem == newItem
        }
    }
}
