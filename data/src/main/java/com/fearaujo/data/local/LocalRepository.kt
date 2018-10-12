package com.fearaujo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fearaujo.model.Venue
import io.reactivex.Observable

@Dao()
interface LocalRepository {

    @Query("SELECT * FROM Venue WHERE loc_city LIKE :searchPrefix")
    fun fetchVenues(searchPrefix: String): Observable<List<Venue>>

    @Query("SELECT * FROM venue")
    fun fetchAll(): Observable<List<Venue>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveVenues(venues: List<Venue>)


}