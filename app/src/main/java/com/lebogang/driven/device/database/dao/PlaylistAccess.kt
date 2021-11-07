package com.lebogang.driven.device.database.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.lebogang.driven.device.database.models.Playlist
import com.lebogang.driven.device.database.models.PlaylistBridge

@Dao
interface PlaylistAccess {

    /**
     * Get all playlist
     * */
    @Query("SELECT * FROM Playlist")
    fun getPlaylist():PagingSource<Int,Playlist>

    @Query("SELECT musicId FROM PlaylistBridge WHERE playlistId =:playlistId")
    fun getMusicIds(playlistId:Int):List<Long>

    /**
     * Add new playlist
     * */
    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = Playlist::class)
    fun addPlaylist(playlist: Playlist)

    /**
     * Add new playlist bridge table instance
     * */
    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = PlaylistBridge::class)
    fun addPlaylistBridge(bridge: PlaylistBridge)

    /**
     * Add new playlist bridge table instances
     * */
    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = PlaylistBridge::class)
    fun addPlaylistBridge(bridge: List<PlaylistBridge>)

    /**
     * Remove playlist
     * */
    @Insert(onConflict = OnConflictStrategy.IGNORE, entity = Playlist::class)
    fun removePlaylist(playlist: Playlist)

    /**
     * Remove playlists
     * */
    @Delete(entity = Playlist::class)
    fun removePlaylist(playlist: List<Playlist>)

    /**
     * Remove playlist bridge
     * */
    @Delete( entity = PlaylistBridge::class)
    fun removePlaylistBridge(bridge: PlaylistBridge)

    /**
     * Remove playlist bridges
     * */
    @Delete( entity = PlaylistBridge::class)
    fun removePlaylistBridge(bridge: List<PlaylistBridge>)

    /**
     * Clear playlist table
     * */
    @Query("DELETE FROM Playlist")
    fun clear()

    /**
     * Clear playlist bridge table
     * */
    @Query("DELETE FROM PlaylistBridge")
    fun clearBridge()
}