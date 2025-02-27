package com.example.testingapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.testingapplication.data.dao.EventDao
import com.example.testingapplication.data.models.*
import com.example.testingapplication.repository.Repository
import com.example.testingapplication.ui.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withTimeout
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.eq
import org.mockito.kotlin.verify
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var eventDao: EventDao

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel(repository, testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createTestEvent(id: String, name: String, venueName: String? = null): Event {
        return Event(
            _embedded = EmbeddedX(
                attractions = emptyList(),
                venues = if (venueName != null) listOf(
                    Venue(
                        _links = Links(self = Self(href = "/discovery/v2/venues/test?locale=en-us")),
                        address = Address(line1 = "123 Test St"),
                        aliases = emptyList(),
                        boxOfficeInfo = BoxOfficeInfo("10am-6pm", "123-456-7890", "No will call"),
                        city = City("Test City"),
                        country = Country("United States", "US"),
                        dmas = emptyList(),
                        generalInfo = GeneralInfo("No cameras", "Kids 3+ need ticket"),
                        id = "venue_$id",
                        images = emptyList(),
                        locale = "en-us",
                        location = Location("0.0", "0.0"),
                        markets = emptyList(),
                        name = venueName,
                        parkingDetail = "Parking available",
                        postalCode = "12345",
                        state = State("Test State", "TS"),
                        test = false,
                        timezone = "America/New_York",
                        type = "venue",
                        upcomingEvents = UpcomingEvents(0, 0, 0, 0, 0),
                        url = "https://example.com/venue"
                    )
                ) else emptyList()
            ),
            _links = Links(self = Self(href = "/discovery/v2/events/$id?locale=en-us")),
            accessibility = null,
            ageRestrictions = null,
            classifications = emptyList(),
            dates = Dates(
                spanMultipleDays = false,
                start = Start(false, false, "2025-03-01T00:00:00Z", "2025-03-01", "00:00:00", false, false),
                status = Status("onsale"),
                timezone = "America/New_York"
            ),
            id = id,
            images = emptyList(),
            locale = "en-us",
            name = name,
            priceRanges = emptyList(),
            products = emptyList(),
            promoter = null,
            promoters = emptyList(),
            sales = Sales(),
            seatmap = Seatmap("seatmap_$id", "https://example.com/seatmap"),
            test = false,
            ticketLimit = null,
            ticketing = Ticketing(AllInclusivePricing(false), "ticketing_$id", SafeTix(false)),
            type = "event",
            url = "https://example.com/event/$id"
        )
    }

    // Helper to wait for LiveData to emit a non-null value
    private suspend fun <T> LiveData<T>.awaitValue(timeoutMs: Long = 2000): T? {
        return withTimeout(timeoutMs) {
            suspendCoroutine { continuation ->
                val observer = object : Observer<T> {
                    override fun onChanged(value: T) {
                        removeObserver(this)
                        continuation.resume(value)
                    }
                }
                observeForever(observer)
            }
        }
    }

    @Test
    fun `getAllEvents posts events on success`() = runTest {
        val events = listOf(createTestEvent("1", "Test Event"))
        val eventsData = EventsData(Embedded(events), page = null)

        val callbackCaptor = argumentCaptor<(Boolean, EventsData?, String) -> Unit>()
        doAnswer { invocation ->
            val callback = invocation.getArgument<(Boolean, EventsData?, String) -> Unit>(2)
            callback(true, eventsData, "")
            null
        }.`when`(repository).getAllEvents(eq(0), eq(40), callbackCaptor.capture())

        val observer = mock<Observer<List<Event>>>()
        viewModel.eventsLiveData.observeForever(observer)

        viewModel.getAllEvents()

        verify(observer).onChanged(events)
        Assert.assertEquals(events, viewModel.eventsLiveData.value)
        Assert.assertNull(viewModel.errorLiveData.value)
    }

    @Test
    fun `getAllEvents posts error on failure`() = runTest {
        val callbackCaptor = argumentCaptor<(Boolean, EventsData?, String) -> Unit>()
        doAnswer { invocation ->
            val callback = invocation.getArgument<(Boolean, EventsData?, String) -> Unit>(2)
            callback(false, null, "Network Error")
            null
        }.`when`(repository).getAllEvents(eq(0), eq(40), callbackCaptor.capture())

        val errorObserver = mock<Observer<String?>>()
        viewModel.errorLiveData.observeForever(errorObserver)

        viewModel.getAllEvents()

        verify(errorObserver).onChanged("Network Error")
        Assert.assertEquals("Network Error", viewModel.errorLiveData.value)
    }

    @Test
    fun `searchEvents returns full list when query is empty`() = runTest {
        val events = listOf(createTestEvent("1", "Test Event", "Venue1"))
        val eventsData = EventsData(Embedded(events), page = null)

        val callbackCaptor = argumentCaptor<(Boolean, EventsData?, String) -> Unit>()
        doAnswer { invocation ->
            val callback = invocation.getArgument<(Boolean, EventsData?, String) -> Unit>(2)
            callback(true, eventsData, "")
            null
        }.`when`(repository).getAllEvents(eq(0), eq(40), callbackCaptor.capture())

        val observer = mock<Observer<List<Event>>>()
        viewModel.eventsLiveData.observeForever(observer)

        viewModel.getAllEvents()
        viewModel.eventsLiveData.awaitValue(timeoutMs = 2000)
        verify(observer).onChanged(events) // From getAllEvents

        viewModel.searchEvents("")
//        verify(observer).onChanged(events)  // From searchEvents (same list)
        Assert.assertEquals(events, viewModel.eventsLiveData.value)
    }

    @Test
    fun `searchEvents filters events by name`() = runTest {
        val events = listOf(
            createTestEvent("1", "Test Event"),
            createTestEvent("2", "Other Event")
        )
        val eventsData = EventsData(Embedded(events), page = null)

        val callbackCaptor = argumentCaptor<(Boolean, EventsData?, String) -> Unit>()
        doAnswer { invocation ->
            val callback = invocation.getArgument<(Boolean, EventsData?, String) -> Unit>(2)
            callback(true, eventsData, "")
            null
        }.`when`(repository).getAllEvents(eq(0), eq(40), callbackCaptor.capture())

        val observer = mock<Observer<List<Event>>>()
        viewModel.eventsLiveData.observeForever(observer)

        viewModel.getAllEvents()
        viewModel.eventsLiveData.awaitValue(timeoutMs = 4000) // Wait for getAllEvents to complete
        viewModel.searchEvents("Test")

        verify(observer).onChanged(events)  // From getAllEvents
        verify(observer).onChanged(listOf(events[0]))  // From searchEvents
        Assert.assertEquals(listOf(events[0]), viewModel.eventsLiveData.value)
    }

    @Test
    fun `searchEvents filters events by venue`() = runTest {
        val events = listOf(
            createTestEvent("1", "Test Event", "Venue1"),
            createTestEvent("2", "Other Event", "Venue2")
        )
        val eventsData = EventsData(Embedded(events), page = null)

        val callbackCaptor = argumentCaptor<(Boolean, EventsData?, String) -> Unit>()
        doAnswer { invocation ->
            val callback = invocation.getArgument<(Boolean, EventsData?, String) -> Unit>(2)
            callback(true, eventsData, "")
            null
        }.`when`(repository).getAllEvents(eq(0), eq(40), callbackCaptor.capture())

        val observer = mock<Observer<List<Event>>>()
        viewModel.eventsLiveData.observeForever(observer)

        viewModel.getAllEvents()
        viewModel.eventsLiveData.awaitValue(timeoutMs = 4000) // Wait for getAllEvents to complete
        viewModel.searchEvents("Venue1")

        verify(observer).onChanged(events)  // From getAllEvents
        verify(observer).onChanged(listOf(events[0]))  // From searchEvents
        Assert.assertEquals(listOf(events[0]), viewModel.eventsLiveData.value)
    }
}