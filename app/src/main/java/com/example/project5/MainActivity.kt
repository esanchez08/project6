package com.example.project5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private lateinit var db: SleepDatabase
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.sleepRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        db = SleepDatabase.getDatabase(this)

        val fab = findViewById<FloatingActionButton>(R.id.fabAddEntry)
        fab.setOnClickListener {
            val intent = Intent(this, AddEntryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
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
}