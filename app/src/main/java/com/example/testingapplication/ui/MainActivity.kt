package com.example.testingapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testingapplication.databinding.ActivityMainBinding
import com.example.testingapplication.ui.adapter.EventsListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var eventAdapter: EventsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        viewModel.eventsLiveData.observe(this, Observer { eventsData ->
            if (eventsData?._embedded?.events?.isNotEmpty() == true) {
                binding.recyclerView.visibility = View.VISIBLE
                binding.emptyStateLayout.visibility = View.GONE
                eventAdapter.updateData(eventsData._embedded.events)
            } else {
                binding.recyclerView.visibility = View.GONE
                binding.emptyStateLayout.visibility = View.VISIBLE
            }
            binding.progressBar.visibility = View.GONE
        })

        viewModel.errorLiveData.observe(this) { errorMessage ->
            if (!errorMessage.isNullOrEmpty()) {
                binding.recyclerView.visibility = View.GONE
                binding.emptyStateLayout.visibility = View.VISIBLE
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
            }
            binding.progressBar.visibility = View.GONE
        }

        binding.retryButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            viewModel.getAllEvents()
        }

        binding.progressBar.visibility = View.VISIBLE
        viewModel.getAllEvents()
    }

    private fun setupRecyclerView() {
        eventAdapter = EventsListAdapter(emptyList())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = eventAdapter
        }
    }

}