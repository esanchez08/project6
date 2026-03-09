package com.example.project5

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.lifecycleScope
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LogFragment : Fragment(R.layout.fragment_log) {

    private lateinit var db: SleepDatabase
    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.sleepRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        db = SleepDatabase.getDatabase(requireContext())

        val fab = view.findViewById<FloatingActionButton>(R.id.fabAddEntry)
        fab.setOnClickListener {
            val intent = Intent(requireContext(), AddEntryActivity::class.java)
            startActivity(intent)
        }

        loadEntries()
    }

    private fun loadEntries() {
        lifecycleScope.launch(Dispatchers.IO) {
            val entries = db.sleepDao().getAllEntries()
            withContext(Dispatchers.Main) {
                recyclerView.adapter = SleepAdapter(entries)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadEntries()
    }
}