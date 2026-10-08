package com.example.bitfit

import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
class SleepEntryAdapter(
    private val sleepEntries: List<SleepEntry>
) : RecyclerView.Adapter<SleepEntryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_sleep, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val sleepEntry = sleepEntries[position]

        holder.dateTextView.text = sleepEntry.date
        holder.sleepDurationTextView.text = "${sleepEntry.hours} hours ${sleepEntry.mins} minutes"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val sleepDurationTextView: TextView =
            itemView.findViewById(R.id.sleepDurationTextView)

    }

    override fun getItemCount(): Int {
        return sleepEntries.size
    }
}