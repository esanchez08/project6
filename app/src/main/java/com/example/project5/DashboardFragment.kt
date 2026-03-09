package com.example.project5

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    private lateinit var db: SleepDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = SleepDatabase.getDatabase(requireContext())

        val avgText = view.findViewById<TextView>(R.id.avgSleep)
        val minText = view.findViewById<TextView>(R.id.minSleep)
        val maxText = view.findViewById<TextView>(R.id.maxSleep)

        lifecycleScope.launch(Dispatchers.IO) {

            val avg = db.sleepDao().getAverageSleep()
            val min = db.sleepDao().getMinSleep()
            val max = db.sleepDao().getMaxSleep()

            withContext(Dispatchers.Main) {
                avgText.text = "Average Sleep: ${avg ?: 0} hrs"
                minText.text = "Minimum Sleep: ${min ?: 0} hrs"
                maxText.text = "Maximum Sleep: ${max ?: 0} hrs"
            }
        }
    }
}