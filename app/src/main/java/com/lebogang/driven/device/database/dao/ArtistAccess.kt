package com.lebogang.driven.device.database.dao

import androidx.paging.PagingSource
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lebogang.driven.device.database.models.Artist

interface ArtistAccess {

    /**
     * Get Artists
     * */
    @Query("SELECT * FROM Artist ORDER BY title ASC")
    fun getArtist():PagingSource<Int,Artist>

    /**
     * Add artist
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArtist(artist: Artist)

    /**
     * Add Artist that are in the list
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArtist(artists: List<Artist>)

    /**
     * Remove artist
     * */
    @Delete
    fun removeArtist(artist: Artist)

    /**
     * Remove artist that are in the list
     * */
    @Delete
    fun removeArtist(artists: List<Artist>)

    /**
     * Reset the table
     * */
    @Query("DELETE FROM Artist")
    fun clear()

}