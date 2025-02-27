package com.example.testingapplication

import com.example.testingapplication.data.dao.EventDao
import com.example.testingapplication.data.models.*
import com.example.testingapplication.repository.ApiService
import com.example.testingapplication.repository.Repository
import com.example.testingapplication.utils.NetworkUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import java.util.concurrent.CountDownLatch

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var networkUtil: NetworkUtil

    @Mock
    private lateinit var eventDao: EventDao

    private lateinit var repository: Repository

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        MockitoAnnotations.openMocks(this)
        repository = Repository(apiService, networkUtil, eventDao)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private fun createTestAttraction(id: String, name: String): Attraction {
        return Attraction(
            _links = Links(self = Self(href = "/discovery/v2/attractions/$id?locale=en-us")),
            aliases = emptyList(),
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
            externalLinks = ExternalLinks(
                facebook = emptyList(),
                homepage = emptyList(),
                instagram = emptyList(),
                twitter = emptyList(),
                wiki = emptyList()
            ),
            id = id,
            images = emptyList(),
            locale = "en-us",
            name = name,
            test = false,
            type = "attraction",
            upcomingEvents = UpcomingEvents(
                _filtered = 0,
                _total = 0,
                ticketmaster = 0,
                tmr = 0
            ),
            url = "https://www.ticketmaster.com/$name-tickets/artist/$id"
        )
    }

    private fun createTestVenue(id: String, name: String): Venue {
        return Venue(
            _links = Links(self = Self(href = "/discovery/v2/venues/$id?locale=en-us")),
            address = Address(line1 = "1111 S. Figueroa St"),
            aliases = emptyList(),
            boxOfficeInfo = BoxOfficeInfo(
                openHoursDetail = "10am to 6pm",
                phoneNumberDetail = "213-742-7340",
                willCallDetail = "No Will Call drop off"
            ),
            city = City(name = "Los Angeles"),
            country = Country(name = "United States Of America", countryCode = "US"),
            dmas = emptyList(),
            generalInfo = GeneralInfo(
                generalRule = "No cameras",
                childRule = "Children age 3+ need ticket"
            ),
            id = id,
            images = emptyList(),
            locale = "en-us",
            location = Location(longitude = "-118.26725300", latitude = "34.04300300"),
            markets = emptyList(),
            name = name,
            parkingDetail = "Designated parking available",
            postalCode = "90015",
            state = State(name = "California", stateCode = "CA"),
            test = false,
            timezone = "America/Los_Angeles",
            type = "venue",
            upcomingEvents = UpcomingEvents(
                _filtered = 0,
                _total = 0,
                ticketmaster = 0,
                tmr = 0
            ),
            url = "https://www.ticketmaster.com/$name-tickets/venue/$id"
        )
    }

    private fun createTestEvent(id: String, name: String): Event {
        return Event(
            _embedded = EmbeddedX(
                attractions = listOf(createTestAttraction("K8vZ91718T0", "Test Attraction")),
                venues = listOf(createTestVenue("KovZpZAEdntA", "Test Venue"))
            ),
            _links = Links(
                self = Self(href = "/discovery/v2/events/$id?locale=en-us")
            ),
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
                status = Status(code = "onsale"),
                timezone = "America/Los_Angeles"
            ),
            id = id,
            images = listOf(
                ImageX(
                    ratio = "16_9",
                    url = "https://s1.ticketm.net/dam/a/test.jpg",
                    width = 640,
                    height = 360,
                    fallback = false
                )
            ),
            locale = "en-us",
            name = name,
            priceRanges = emptyList(),
            products = emptyList(),
            promoter = null,
            promoters = emptyList(),
            sales = Sales(
                public = Public(
                    startDateTime = "2024-08-22T20:00:00Z",
                    startTBD = false,
                    startTBA = false,
                    endDateTime = "2025-03-16T19:30:00Z"
                )
            ),
            seatmap = Seatmap(
                id = "seatmap_$id",
                staticUrl = "https://example.com/seatmap/$id"
            ),
            test = false,
            ticketLimit = null,
            ticketing = Ticketing(
                allInclusivePricing = AllInclusivePricing(enabled = true),
                id = "ticketing_$id",
                safeTix = SafeTix(enabled = true)
            ),
            type = "event",
            url = "https://www.ticketmaster.com/event/$id"
        )
    }

    @Test
    fun `getAllEvents fetches from API when internet available`() = runTest {
        `when`(networkUtil.isInternetAvailable()).thenReturn(true)
        val events = listOf(createTestEvent("1", "Test Event"))
        val eventsData = EventsData(
            Embedded(events),
            page = null
        )
        `when`(apiService.getAllEvents(0, 40, "DW0E98NrxUIfDDtNN7ijruVSm60ryFLX")).thenReturn(Response.success(eventsData))

        val latch = CountDownLatch(1)
        repository.getAllEvents(0, 40) { isSuccess, data, _ ->
            Assert.assertTrue(isSuccess)
            Assert.assertEquals(eventsData, data)
            latch.countDown()
        }
        latch.await()

        verify(eventDao).insertEvents(events)
    }

    @Test
    fun `getAllEvents fetches from local DB when no internet`() = runTest {
        `when`(networkUtil.isInternetAvailable()).thenReturn(false)
        val events = listOf(createTestEvent("1", "Test Event"))
        `when`(eventDao.getAllEvents()).thenReturn(events)

        val latch = CountDownLatch(1)
        repository.getAllEvents(0, 40) { isSuccess, data, message ->
            Assert.assertTrue(isSuccess)
            Assert.assertEquals(events, data?._embedded?.events)
            Assert.assertEquals("", message)
            latch.countDown()
        }
        latch.await()
    }

    @Test
    fun `getAllEvents returns error when API fails and no local data`() = runTest {
        `when`(networkUtil.isInternetAvailable()).thenReturn(true)
        `when`(apiService.getAllEvents(0, 40, "DW0E98NrxUIfDDtNN7ijruVSm60ryFLX")).thenThrow(RuntimeException("API Error"))
        `when`(eventDao.getAllEvents()).thenReturn(emptyList())

        val latch = CountDownLatch(1)
        repository.getAllEvents(0, 40) { isSuccess, data, message ->
            Assert.assertFalse(isSuccess)
            Assert.assertNull(data)
            Assert.assertEquals("No events found", message)
            latch.countDown()
        }
        latch.await()
    }
}