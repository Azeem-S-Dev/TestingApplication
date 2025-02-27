package com.example.testingapplication.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.testingapplication.R
import com.example.testingapplication.databinding.EventCardBinding
import com.example.testingapplication.data.models.Event
import com.example.testingapplication.data.models.ImageX

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
            loadImage(binding, binding.root.context, event.images) { isSuccess ->
                if (isSuccess) {
                    binding.eventImage.scaleType = ImageView.ScaleType.CENTER
                    binding.imageProgressBar.visibility = View.GONE
                } else {
                    binding.imageProgressBar.visibility = View.GONE
                    binding.eventImage.scaleType = ImageView.ScaleType.CENTER_INSIDE
                }
            }
        }
    }
    // Function to select the best image based on device screen size
    fun selectBestImage(images: List<ImageX>?, context: Context, displayMetrics: DisplayMetrics?): String? {
        if (images.isNullOrEmpty()) return null

        // Get device screen dimensions
        var receivedMetrics = displayMetrics
        if (receivedMetrics == null) {
            receivedMetrics = context.resources.displayMetrics
        }
        val screenWidth = receivedMetrics!!.widthPixels
        val screenHeight = receivedMetrics.heightPixels
        val screenAspectRatio = screenWidth.toFloat() / screenHeight.toFloat()

        // Target aspect ratio (e.g., 16:9 = 1.777, 3:2 = 1.5, 4:3 = 1.333)
        val targetAspectRatios = mapOf(
            "16_9" to 16f / 9f,
            "3_2" to 3f / 2f,
            "4_3" to 4f / 3f
        )

        // Filter images by closest aspect ratio and sufficient resolution
        val suitableImages = images.filter { image ->
            val imageAspectRatio = targetAspectRatios[image.ratio] ?: return@filter false
            // Check if resolution is reasonable (e.g., at least half screen width, but not excessively large)
            image.width >= screenWidth / 2 && image.width <= screenWidth * 2 &&
                    kotlin.math.abs(imageAspectRatio - screenAspectRatio) < 0.3 // Tolerance for aspect ratio
        }.sortedByDescending { it.width * it.height } // Sort by resolution (highest first)

        // Return the URL of the best match, or fallback to the highest resolution 16:9 if no match
        return suitableImages.firstOrNull()?.url ?: images
            .filter { it.ratio == "16_9" }
            .maxByOrNull { it.width * it.height }?.url
    }

    fun loadImage(
        binding: EventCardBinding,
        context: Context,
        images: List<ImageX>,
        requestManager: RequestManager = Glide.with(context),
        callBack: (isSuccess: Boolean) -> Unit
    ) {
        val displayMetrics = context.resources.displayMetrics
        val imageUrl = selectBestImage(images, context, displayMetrics)

        requestManager
            .load(imageUrl)
            .error(R.drawable.error_image2)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    callBack(false)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    callBack(true)
                    return false
                }
            })
            .into(binding.eventImage)
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