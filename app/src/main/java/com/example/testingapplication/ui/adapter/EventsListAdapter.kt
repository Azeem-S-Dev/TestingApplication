package com.example.testingapplication.ui.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.testingapplication.R
import com.example.testingapplication.databinding.EventCardBinding
import com.example.testingapplication.models.Event

class EventsListAdapter(private var eventList: List<Event>) : RecyclerView.Adapter<EventsListAdapter.EventViewHolder>() {

    inner class EventViewHolder(private val binding: EventCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            binding.eventTitle.text = event.name
            binding.eventDate.text = event.dates.start.localDate
            val venues = event._embedded.venues

            if (venues.isEmpty()) {
                binding.eventLocation.visibility = View.GONE
                binding.eventCity.visibility = View.GONE
            } else {
                binding.eventLocation.visibility = View.VISIBLE
                binding.eventCity.visibility = View.VISIBLE

                val venueNames = venues.joinToString(separator = ", ") { it.name }
                binding.eventLocation.text = venueNames

                val states = venues.map { it.state.name + ", " + it.state.stateCode }
                binding.eventCity.text = states.joinToString(separator = " - ")
            }

            binding.imageProgressBar.visibility = View.VISIBLE
            loadImage(binding, event.images.getOrNull(0)?.url, event)
        }
    }

    fun loadImage(binding: EventCardBinding, imageUrl: String?, event: Event) {
        Glide.with(binding.root.context)
            .load(imageUrl)
            .error(R.drawable.error_image2)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    if (event.images.size > 1 && model == event.images[0].url) {
                        loadImage(binding, event.images[1].url, event)
                    } else {
                        binding.imageProgressBar.visibility = View.GONE
                        binding.eventImg.scaleType = ImageView.ScaleType.CENTER_INSIDE
                    }
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.eventImg.scaleType = ImageView.ScaleType.CENTER
                    binding.imageProgressBar.visibility = View.GONE
                    return false
                }
            })
            .into(binding.eventImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = EventCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    override fun getItemCount(): Int = eventList.size

    fun updateData(newList: List<Event>) {
        eventList = newList
        notifyDataSetChanged()
    }
}