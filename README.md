**Project Overview**
Event Explorer is a simple Android mobile application that interacts with the Ticketmaster Discovery API to fetch and display event data. The app allows users to view a list of events retrieved from the API and search for events by name or venue. It features a clean, user-friendly interface with offline support via local caching using Room database.

**Purpose**
The primary goal of this project is to demonstrate the integration of a RESTful API (Ticketmaster Discovery API) into an Android application, showcasing event data retrieval and presentation in a RecyclerView with real-time search functionality.

**Features**
Event Listing: Fetches and displays a list of events from the Ticketmaster API.
Search Functionality: Allows users to filter events by event name or venue name using a SearchView.
Offline Support: Caches event data locally using Room database, enabling event display when no internet connection is available.
Dynamic Image Loading: Selects and displays the most suitable event image based on device screen size using Glide.
Error Handling: Shows appropriate messages for network errors or empty event lists.
Retry Option: Provides a retry button to refresh event data when no events are available or an error occurs.

**Minimum Display Requirements**
Each event in the list includes:
  _Name_: Event title.
  _Image_: The most suitable image selected dynamically based on screen resolution and aspect ratio.
  _Additional_ Fields: Event _date_, _venue name_(s), and venue location (_state_ and _state code_).

**Testing**
Unit tests are located in _app/src/test/java/com/example/testingapplication_. To run tests:
  ./gradlew test


**Project Structure**

com.example.testingapplication
├── data
│   ├── dao              # Room DAO for local storage
│   ├── database         # Room database configuration
│   ├── models           # Data models for API responses
│   └── converters       # Type converters for Room
├── di                   # Hilt dependency injection modules
├── repository           # API and database interaction logic
├── ui                   # Activity and ViewModel
│   ├── adapter          # RecyclerView adapter for event display
│   └── MainActivity.kt  # Main UI component
└── utils                # Utility classes (e.g., NetworkUtil)

**Technical Details**
_Architecture:_ MVVM (Model-View-ViewModel) with Repository pattern.
_Dependency Injection:_ Hilt for managing dependencies.
_Networking:_ Retrofit with OkHttp for API calls.
_Local Storage:_ Room database with type converters for complex data structures.
_Image Handling:_ Glide selects the best image based on screen size and aspect ratio (16:9, 3:2, 4:3).
_Testing:_ Unit tests implemented using Mockito and Kotlin Coroutines Test.
