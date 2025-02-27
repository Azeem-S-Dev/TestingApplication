package com.example.testingapplication

import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import android.widget.ImageView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.request.RequestListener
import com.example.testingapplication.data.models.*
import com.example.testingapplication.databinding.EventCardBinding
import com.example.testingapplication.ui.adapter.EventsListAdapter
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.*
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import androidx.constraintlayout.widget.ConstraintLayout

@RunWith(RobolectricTestRunner::class)
class EventsListAdapterTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var adapter: EventsListAdapter

    @Mock
    private lateinit var binding: EventCardBinding

    @Mock
    private lateinit var eventImage: android.widget.ImageView

    @Mock
    private lateinit var imageProgressBar: android.widget.ProgressBar

    @Mock
    private lateinit var rootView: ConstraintLayout

    @Before
    fun setUp() {
        org.mockito.MockitoAnnotations.openMocks(this)
        adapter = EventsListAdapter(emptyList())

        // Mock the root view and its getContext() method
//        `when`(binding.root).thenReturn(rootView)
//        `when`(rootView.getContext()).thenReturn(RuntimeEnvironment.getApplication())

        `when`(binding.root).thenReturn(rootView)
        `when`(rootView.context).thenReturn(RuntimeEnvironment.getApplication())
//        `when`(binding.eventImage).thenReturn(eventImage)
//        `when`(binding.imageProgressBar).thenReturn(imageProgressBar)
        // Set up the binding mock with fields directly
//        binding.eventImage = eventImage
//        binding.imageProgressBar = imageProgressBar
//        binding.root = rootView

        // Mock the getter methods for binding properties
//        `when`(binding.eventImage).thenReturn(eventImage)
//        `when`(binding.imageProgressBar).thenReturn(imageProgressBar)
    }

    private fun createTestEvent(id: String, name: String): Event {
        return Event(
            _embedded = EmbeddedX(
                attractions = emptyList(),
                venues = listOf(
                    Venue(
                        _links = Links(self = Self(href = "/discovery/v2/venues/KovZpZAEdntA?locale=en-us")),
                        address = Address(line1 = "1111 S. Figueroa St"),
                        aliases = emptyList(),
                        boxOfficeInfo = BoxOfficeInfo("10am-6pm", "213-742-7340", "No will call"),
                        city = City("Los Angeles"),
                        country = Country("United States Of America", "US"),
                        dmas = emptyList(),
                        generalInfo = GeneralInfo("No cameras", "Kids 3+ need ticket"),
                        id = "KovZpZAEdntA",
                        images = emptyList(),
                        locale = "en-us",
                        location = Location("-118.26725300", "34.04300300"),
                        markets = emptyList(),
                        name = "Crypto.com Arena",
                        parkingDetail = "Parking available",
                        postalCode = "90015",
                        state = State("California", "CA"),
                        test = false,
                        timezone = "America/Los_Angeles",
                        type = "venue",
                        upcomingEvents = UpcomingEvents(0, 0, 0, 0, 0),
                        url = "https://www.ticketmaster.com/cryptocom-arena-tickets-los-angeles/venue/360457"
                    )
                )
            ),
            _links = Links(self = Self(href = "/discovery/v2/events/$id?locale=en-us")),
            accessibility = null,
            ageRestrictions = null,
            classifications = listOf(
                ClassificationX(
                    primary = true,
                    segment = Segment(id = "KZFzniwnSyZfZ7v7nE", name = "Sports"),
                    genre = Genre(id = "KnvZfZ7vAde", name = "Basketball"),
                    subGenre = SubGenre(id = "KZazBEonSMnZfZ7vFJA", name = "NBA"),
                    type = Type(id = "KZAyXgnZfZ7v7l1", name = "Group"),
                    subType = SubType(id = "KZFzBErXgnZfZ7vA7d", name = "Team"),
                    family = false
                )
            ),
            dates = Dates(
                spanMultipleDays = false,
                start = Start(
                    dateTBA = false,
                    dateTBD = false,
                    dateTime = "2025-03-16T19:30:00Z",
                    localDate = "2025-03-16",
                    localTime = "12:30:00",
                    noSpecificTime = false,
                    timeTBA = false
                ),
                status = Status("onsale"),
                timezone = "America/Los_Angeles"
            ),
            id = id,
            images = listOf(
                ImageX(null, false, 56, "16_9", "https://s1.ticketm.net/dam/a/0a6/1a8879f7-58c5-415d-a6a4-8c9a794970a6_1277361_RECOMENDATION_16_9.jpg", 100),
                ImageX(null, false, 683, "3_2", "https://s1.ticketm.net/dam/a/0a6/1a8879f7-58c5-415d-a6a4-8c9a794970a6_1277361_TABLET_LANDSCAPE_3_2.jpg", 1024),
                ImageX(null, false, 576, "16_9", "https://s1.ticketm.net/dam/a/0a6/1a8879f7-58c5-415d-a6a4-8c9a794970a6_1277361_TABLET_LANDSCAPE_16_9.jpg", 1024),
                ImageX(null, false, 639, "16_9", "https://s1.ticketm.net/dam/a/0a6/1a8879f7-58c5-415d-a6a4-8c9a794970a6_1277361_RETINA_LANDSCAPE_16_9.jpg", 1136),
                ImageX(null, false, 427, "3_2", "https://s1.ticketm.net/dam/a/0a6/1a8879f7-58c5-415d-a6a4-8c9a794970a6_1277361_RETINA_PORTRAIT_3_2.jpg", 640),
                ImageX(null, false, 225, "4_3", "https://s1.ticketm.net/dam/a/0a6/1a8879f7-58c5-415d-a6a4-8c9a794970a6_1277361_CUSTOM.jpg", 305),
                ImageX(null, false, 360, "16_9", "https://s1.ticketm.net/dam/a/0a6/1a8879f7-58c5-415d-a6a4-8c9a794970a6_1277361_RETINA_PORTRAIT_16_9.jpg", 640),
                ImageX(null, false, 203, "3_2", "https://s1.ticketm.net/dam/a/0a6/1a8879f7-58c5-415d-a6a4-8c9a794970a6_1277361_ARTIST_PAGE_3_2.jpg", 305),
                ImageX(null, false, 1152, "16_9", "https://s1.ticketm.net/dam/a/0a6/1a8879f7-58c5-415d-a6a4-8c9a794970a6_SOURCE", 2048),
                ImageX(null, false, 115, "16_9", "https://s1.ticketm.net/dam/a/0a6/1a8879f7-58c5-415d-a6a4-8c9a794970a6_1277361_EVENT_DETAIL_PAGE_16_9.jpg", 205)
            ),
            locale = "en-us",
            name = name,
            priceRanges = emptyList(),
            products = emptyList(),
            promoter = null,
            promoters = emptyList(),
            sales = Sales(),
            seatmap = Seatmap("seatmap_$id", "https://mapsapi.tmol.io/maps/geometry/3/event/$id/staticImage"),
            test = false,
            ticketLimit = null,
            ticketing = Ticketing(AllInclusivePricing(true), "ticketing_$id", SafeTix(true)),
            type = "event",
            url = "https://www.ticketmaster.com/event/$id"
        )
    }

    @Test
    fun `selectBestImage returns null when images list is empty`() {
        val context = RuntimeEnvironment.getApplication()
        val event = createTestEvent("1", "Test Event").copy(images = emptyList())
        val result = adapter.selectBestImage(event.images, context, null)
        Assert.assertNull(result)
    }

    @Test
    fun `selectBestImage selects highest resolution 16_9 image when no suitable match`() {
        val context = RuntimeEnvironment.getApplication()
        val displayMetrics = DisplayMetrics().apply { widthPixels = 1080; heightPixels = 1920 }
//        `when`(context.resources.displayMetrics).thenReturn(displayMetrics)

        val event = createTestEvent("1", "Test Event")
        val result = adapter.selectBestImage(event.images, context, displayMetrics)
        Assert.assertEquals("https://s1.ticketm.net/dam/a/0a6/1a8879f7-58c5-415d-a6a4-8c9a794970a6_SOURCE", result)
    }

    @Test
    fun `selectBestImage selects suitable image based on screen size`() {
        val context = RuntimeEnvironment.getApplication()
        val displayMetrics = DisplayMetrics().apply { widthPixels = 100; heightPixels = 56 }
//        `when`(context.resources.displayMetrics).thenReturn(displayMetrics)

        val event = createTestEvent("1", "Test Event")
        val result = adapter.selectBestImage(event.images, context, displayMetrics)
        Assert.assertEquals("https://s1.ticketm.net/dam/a/0a6/1a8879f7-58c5-415d-a6a4-8c9a794970a6_1277361_RECOMENDATION_16_9.jpg", result)
    }

    @Test
    fun `loadImage handles successful load`() {
        val context = RuntimeEnvironment.getApplication()
        val event = createTestEvent("1", "Test Event")
        val images = event.images

        val mockRequestManager = mockGlideSuccess(binding)
        adapter.loadImage(binding, context, images, mockRequestManager) {}

    }

    @Test
    fun `loadImage handles failure`() {
        val context = RuntimeEnvironment.getApplication()
        val event = createTestEvent("1", "Test Event")
        val images = event.images

        val mockRequestManager = mockGlideFailure(binding)
        adapter.loadImage(binding, context, images, mockRequestManager) {}

    }

    @Test
    fun `updateData updates list and notifies adapter`() {
        val newList = listOf(createTestEvent("1", "Event 1"))
        adapter.updateData(newList)
        Assert.assertEquals(1, adapter.itemCount)
    }

    private fun mockGlideSuccess(binding: EventCardBinding): RequestManager {
        val mockRequestManager: RequestManager = mock()
        val mockRequestBuilder: RequestBuilder<Drawable> = mock()

        `when`(mockRequestManager.load(anyString())).thenReturn(mockRequestBuilder)
        `when`(mockRequestBuilder.error(anyInt())).thenReturn(mockRequestBuilder)
        `when`(mockRequestBuilder.listener(any())).thenAnswer {
            val listener = it.arguments[0] as RequestListener<Drawable>
            listener.onResourceReady(null, null, null, DataSource.REMOTE, false)
            mockRequestBuilder
        }
        `when`(mockRequestBuilder.into(binding.eventImage)).thenReturn(mock())

        return mockRequestManager
    }

    private fun mockGlideFailure(binding: EventCardBinding): RequestManager {
        val mockRequestManager: RequestManager = mock()
        val mockRequestBuilder: RequestBuilder<Drawable> = mock()

        `when`(mockRequestManager.load(anyString())).thenReturn(mockRequestBuilder)
        `when`(mockRequestBuilder.error(anyInt())).thenReturn(mockRequestBuilder)
        `when`(mockRequestBuilder.listener(any())).thenAnswer {
            val listener = it.arguments[0] as RequestListener<Drawable>
            listener.onLoadFailed(null, null, null, false)
            mockRequestBuilder
        }
        `when`(mockRequestBuilder.into(binding.eventImage)).thenReturn(mock())

        return mockRequestManager
    }
}