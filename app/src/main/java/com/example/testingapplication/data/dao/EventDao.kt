package com.example.testingapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testingapplication.data.models.Event

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvents(events: List<Event>)

    @Query("SELECT * FROM events")
    fun getAllEvents(): List<Event>?

    @Query("DELETE FROM events")
    fun deleteAllEvents()
}