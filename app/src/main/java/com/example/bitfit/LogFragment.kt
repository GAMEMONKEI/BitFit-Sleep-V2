package com.example.bitfit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class LogFragment : Fragment(R.layout.fragment_log) {

    private lateinit var entriesRecyclerView: RecyclerView
    private lateinit var addEntryButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        entriesRecyclerView = view.findViewById(R.id.entriesRecyclerView)
        addEntryButton = view.findViewById(R.id.addEntryButton)

        entriesRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        addEntryButton.setOnClickListener {
            val intent = Intent(requireContext(), AddEntryActivity::class.java)
            startActivity(intent)
        }

        val sleepEntryDao = (requireActivity().application as BitFitApplication).db.sleepEntryDao()

        viewLifecycleOwner.lifecycleScope.launch {
            sleepEntryDao.getAll().collect { sleepEntries ->
                entriesRecyclerView.adapter = SleepEntryAdapter(sleepEntries)
            }
        }
    }
}