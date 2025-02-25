package com.example.testingapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.SearchView
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
    private var isSearching = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        viewModel.eventsLiveData.observe(this, Observer { eventsList ->
            if (eventsList.isNotEmpty()) {
                binding.recyclerView.visibility = View.VISIBLE
                binding.emptyStateLayout.visibility = View.GONE
                eventAdapter.updateData(eventsList)
            } else {
                binding.recyclerView.visibility = View.GONE
                binding.emptyStateLayout.visibility = View.VISIBLE

                if (isSearching) {
                    binding.errorText.text = "No results found for your search."
                    binding.retryButton.visibility = View.GONE
                } else {
                    binding.errorText.text = "No events available at the moment."
                    binding.retryButton.visibility = View.VISIBLE
                }
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

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    isSearching = it.isNotEmpty()
                    viewModel.searchEvents(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    isSearching = it.isNotEmpty()
                    viewModel.searchEvents(it)
                }
                return true
            }
        })

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