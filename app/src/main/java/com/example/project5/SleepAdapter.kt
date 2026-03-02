package com.example.project5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SleepAdapter(private val sleepList: List<SleepEntry>) :
    RecyclerView.Adapter<SleepAdapter.SleepViewHolder>() {

    class SleepViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateText: TextView = itemView.findViewById(R.id.dateText)
        val hoursText: TextView = itemView.findViewById(R.id.hoursText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SleepViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sleep_item, parent, false)
        return SleepViewHolder(view)
    }

    override fun onBindViewHolder(holder: SleepViewHolder, position: Int) {
        val entry = sleepList[position]
        holder.dateText.text = entry.date
        holder.hoursText.text = "${entry.hoursSlept} hours"
    }

    override fun getItemCount(): Int = sleepList.size
}