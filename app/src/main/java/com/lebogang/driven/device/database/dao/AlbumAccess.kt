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
     * Add album
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAlbum(album: Album)

    /**
     * Add albums that are in the list
     * */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAlbums(albums: List<Album>)

    /**
     * Remove albums that are in the list
     * */
    @Delete
    fun removeAlbum(albums:List<Album>)

    /**
     * Remove album
     * */
    @Delete
    fun removeAlbum(album: Album)

    /**
     * Reset the table
     * */
    @Query("DELETE FROM Album")
    fun clear()
}