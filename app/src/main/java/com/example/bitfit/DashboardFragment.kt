package com.example.bitfit

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val totalEntries = view.findViewById<TextView>(R.id.totalEntries)
        val averageSleep = view.findViewById<TextView>(R.id.averageSleep)

        val db = (requireActivity().application as BitFitApplication).db

        viewLifecycleOwner.lifecycleScope.launch {
            db.sleepEntryDao().getAll().collect { entries ->

                totalEntries.text = "Total Entries: ${entries.size}"

                if (entries.isNotEmpty()) {
                    val totalMinutes = entries.sumOf { it.hours * 60 + it.mins }
                    val average = totalMinutes.toDouble() / entries.size / 60

                    averageSleep.text = "Average Sleep: %.1f hours".format(average)
                } else {
                    averageSleep.text = "Average Sleep: 0 hours"
                }
            }
        }
    }
}