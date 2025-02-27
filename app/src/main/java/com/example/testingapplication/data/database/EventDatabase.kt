package com.example.testingapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testingapplication.data.converters.AccessibilityTypeConverter
import com.example.testingapplication.data.converters.AddressTypeConverter
import com.example.testingapplication.data.converters.AgeRestrictionsTypeConverter
import com.example.testingapplication.data.converters.AllInclusivePricingTypeConverter
import com.example.testingapplication.data.converters.AttractionListTypeConverter
import com.example.testingapplication.data.converters.AttractionXListTypeConverter
import com.example.testingapplication.data.converters.BoxOfficeInfoTypeConverter
import com.example.testingapplication.data.converters.CityTypeConverter
import com.example.testingapplication.data.converters.ClassificationXListTypeConverter
import com.example.testingapplication.data.converters.CountryTypeConverter
import com.example.testingapplication.data.converters.DatesTypeConverter
import com.example.testingapplication.data.converters.DmaListTypeConverter
import com.example.testingapplication.data.converters.EmbeddedXTypeConverter
import com.example.testingapplication.data.converters.EventListTypeConverter
import com.example.testingapplication.data.converters.EventTypeConverter
import com.example.testingapplication.data.converters.ExternalLinksTypeConverter
import com.example.testingapplication.data.converters.GeneralInfoTypeConverter
import com.example.testingapplication.data.converters.GenreTypeConverter
import com.example.testingapplication.data.converters.ImageXListTypeConverter
import com.example.testingapplication.data.converters.LinksTypeConverter
import com.example.testingapplication.data.converters.ListConverters
import com.example.testingapplication.data.converters.LocationTypeConverter
import com.example.testingapplication.data.converters.MarketListTypeConverter
import com.example.testingapplication.data.converters.PresaleListTypeConverter
import com.example.testingapplication.data.converters.PriceRangeListTypeConverter
import com.example.testingapplication.data.converters.ProductListTypeConverter
import com.example.testingapplication.data.converters.PromoterListTypeConverter
import com.example.testingapplication.data.converters.PromoterTypeConverter
import com.example.testingapplication.data.converters.PublicTypeConverter
import com.example.testingapplication.data.converters.SafeTixTypeConverter
import com.example.testingapplication.data.converters.SalesTypeConverter
import com.example.testingapplication.data.converters.SeatmapTypeConverter
import com.example.testingapplication.data.converters.SegmentTypeConverter
import com.example.testingapplication.data.converters.SelfTypeConverter
import com.example.testingapplication.data.converters.StartTypeConverter
import com.example.testingapplication.data.converters.StatusTypeConverter
import com.example.testingapplication.data.converters.SubGenreTypeConverter
import com.example.testingapplication.data.converters.SubTypeTypeConverter
import com.example.testingapplication.data.converters.TicketLimitTypeConverter
import com.example.testingapplication.data.converters.TicketingTypeConverter
import com.example.testingapplication.data.converters.TypeTypeConverter
import com.example.testingapplication.data.converters.UpcomingEventsTypeConverter
import com.example.testingapplication.data.converters.UrlListListTypeConverter
import com.example.testingapplication.data.converters.VenueListTypeConverter
import com.example.testingapplication.data.converters.VenueXListTypeConverter
import com.example.testingapplication.data.dao.EventDao
import com.example.testingapplication.data.models.Event

@Database(
    entities = [
        Event::class
    ],
    version = 4,
    exportSchema = false
)
@TypeConverters(
    EventTypeConverter::class,
    EventListTypeConverter::class,
    EmbeddedXTypeConverter::class,
    AttractionListTypeConverter::class,
    VenueListTypeConverter::class,
    LinksTypeConverter::class,
    AttractionXListTypeConverter::class,
    SelfTypeConverter::class,
    VenueXListTypeConverter::class,
    ClassificationXListTypeConverter::class,
    GenreTypeConverter::class,
    SegmentTypeConverter::class,
    SubGenreTypeConverter::class,
    SubTypeTypeConverter::class,
    TypeTypeConverter::class,
    ExternalLinksTypeConverter::class,
    UrlListListTypeConverter::class,
    ImageXListTypeConverter::class,
    UpcomingEventsTypeConverter::class,
    AddressTypeConverter::class,
    BoxOfficeInfoTypeConverter::class,
    CityTypeConverter::class,
    CountryTypeConverter::class,
    DmaListTypeConverter::class,
    GeneralInfoTypeConverter::class,
    LocationTypeConverter::class,
    MarketListTypeConverter::class,
    AccessibilityTypeConverter::class,
    AgeRestrictionsTypeConverter::class,
    DatesTypeConverter::class,
    StartTypeConverter::class,
    StatusTypeConverter::class,
    PriceRangeListTypeConverter::class,
    ProductListTypeConverter::class,
    PromoterTypeConverter::class,
    PromoterListTypeConverter::class,
    SalesTypeConverter::class,
    PresaleListTypeConverter::class,
    PublicTypeConverter::class,
    SeatmapTypeConverter::class,
    TicketLimitTypeConverter::class,
    TicketingTypeConverter::class,
    AllInclusivePricingTypeConverter::class,
    SafeTixTypeConverter::class,
    ListConverters::class,
)
abstract class EventDatabase: RoomDatabase() {
    abstract fun eventDao(): EventDao

    companion object {
        const val DB_NAME = "event_database.db"
    }
}