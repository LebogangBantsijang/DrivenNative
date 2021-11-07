package com.lebogang.driven.device.database.dao

import androidx.paging.PagingSource
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lebogang.driven.device.database.models.Album

interface AlbumAccess {

    /**
     * Get albums
     * */
    @Query("SELECT * FROM Album ORDER BY title ASC")
    fun getAlbums():PagingSource<Int,Album>

    /**
     * Get album with the given id
     * This will be used for view song album option.
     * We'll pause the main thread and proceed when finished
     * */
    @Query("SELECT * FROM album WHERE id =:id")
    fun getAlbum(id:Long):Album

    /**
     * Add album
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAlbum(album: Album):Int

    /**
     * Add albums that are in the list
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAlbums(albums: List<Album>):Int

    /**
     * Remove albums that are in the list
     * */
    @Delete
    fun removeAlbum(albums:List<Album>):Int

    /**
     * Remove album
     * */
    @Delete
    fun removeAlbum(album: Album):Int

    /**
     * Reset the table
     * */
    @Query("DELETE FROM Album")
    fun clear()
}