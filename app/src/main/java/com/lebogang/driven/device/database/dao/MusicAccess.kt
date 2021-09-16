package com.lebogang.driven.device.database.dao

import androidx.paging.PagingSource
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lebogang.driven.device.database.models.Music

interface MusicAccess {
    // add search
    /**
     * Get all songs
     * */
    @Query("SELECT * FROM Music ORDER BY title ASC")
    fun getMusic():PagingSource<Int,Music>

    /**
     * Get Songs with ids that match the ones in the list
     * */
    @Query("SELECT * FROM Music WHERE id IN(:id) ORDER BY title ASC")
    fun getMusic(id:List<Long>):PagingSource<Int,Music>

    /**
     * Get songs with an album id that matches the one in the params
     * */
    @Query("SELECT * FROM Music WHERE albumId =:id ORDER BY title ASC")
    fun getMusicAlbum(id:Long):PagingSource<Int,Music>

    /**
     * Get songs with an artist id that matches the one in the params
     * */
    @Query("SELECT * FROM music WHERE artistId =:id ORDER BY title ASC")
    fun getMusicArtist(id:Long):PagingSource<Int,Music>

    /**
     * Add music
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMusic(music: Music)

    /**
     * Add music
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMusic(music:List<Music>)

    /**
     * Remove music
     * */
    @Delete
    fun removeMusic(music: Music)

    /**
     * Remove music
     * */
    @Delete
    fun removeMusic(music: List<Music>)

    /**
     * Reset the table
     * */
    @Query("DELETE FROM Music")
    fun clear()

}