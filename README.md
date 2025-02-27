# Event Explorer

Event Explorer is an Android mobile application that interacts with the Ticketmaster Discovery API to fetch and display event data. The app allows users to view a list of events retrieved from the API and search for events by name or venue. It features a clean, user-friendly interface with offline support via local caching using the Room database.

## Purpose

The primary goal of this project is to demonstrate the integration of a RESTful API (Ticketmaster Discovery API) into an Android application. It showcases event data retrieval and presentation in a RecyclerView with real-time search functionality.

## Features

- **Event Listing:** Fetches and displays a list of events from the Ticketmaster API.
- **Search Functionality:** Allows users to filter events by event name or venue name using a `SearchView`.
- **Offline Support:** Caches event data locally using the Room database, enabling event display when no internet connection is available.
- **Dynamic Image Loading:** Selects and displays the most suitable event image based on device screen size using Glide.
- **Error Handling:** Shows appropriate messages for network errors or empty event lists.
- **Retry Option:** Provides a retry button to refresh event data when no events are available or an error occurs.

## Minimum Display Requirements

Each event in the list includes the following details:

- **Name:** Event title.
- **Image:** The most suitable image selected dynamically based on screen resolution and aspect ratio.
- **Additional Fields:**
  - Event date.
  - Venue name(s).
  - Venue location (state and state code).

## Testing

Unit tests are located in the `app/src/test/java/com/example/testingapplication` directory. To run tests, use the following command:

```bash
./gradlew test
```


## Project Structure**

```plaintext
com.example.testingapplication
├── data
│   ├── dao            # Room DAO for local storage
│   ├── database       # Room database configuration
│   ├── models         # Data models for API responses
│   └── converters     # Type converters for Room
│
├── di                 # Hilt dependency injection modules
│
├── repository         # API and database interaction logic
│
├── ui
│   ├── adapter        # RecyclerView adapter for event display
│   └── MainActivity.kt # Main UI component
│
└── utils              # Utility classes (e.g., NetworkUtil)
```

## Technical Details
- Architecture: MVVM (Model-View-ViewModel) with the Repository pattern.
- Dependency Injection: Hilt for managing dependencies.
- Networking: Retrofit with OkHttp for API calls.
- Local Storage: Room database with type converters for complex data structures.
- Image Handling: Glide selects the best image based on screen size and aspect ratio (16:9, 3:2, 4:3).
- Testing: Unit tests implemented using Mockito and Kotlin Coroutines Test.

## License
This project is licensed under the MIT License.
