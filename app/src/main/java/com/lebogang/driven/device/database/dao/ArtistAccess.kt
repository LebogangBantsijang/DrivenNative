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
     * Get album with the given id
     * This will be used for view song album option.
     * We'll pause the main thread and proceed when finished
     * */
    @Query("SELECT * FROM Artist WHERE id =:id")
    fun getArtist(id:Long):Artist

    /**
     * Add artist
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArtist(artist: Artist):Int

    /**
     * Add Artist that are in the list
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addArtist(artists: List<Artist>):Int

    /**
     * Remove artist
     * */
    @Delete
    fun removeArtist(artist: Artist):Int

    /**
     * Remove artist that are in the list
     * */
    @Delete
    fun removeArtist(artists: List<Artist>):Int

    /**
     * Reset the table
     * */
    @Query("DELETE FROM Artist")
    fun clear()

}