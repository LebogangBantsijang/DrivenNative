package com.lebogang.driven.device.database

import android.content.Context
import com.lebogang.driven.DrivenApplication
import com.lebogang.driven.DrivenDatabase
import com.lebogang.driven.device.database.models.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AccessHelper(private val application: DrivenApplication,private val scope: CoroutineScope) {
    private val database:DrivenDatabase by lazy {application.localDatabase}

    fun getMusic() = database.getMusicAccess().getMusic()

    fun getMusic(query:String) = database.getMusicAccess().getMusic()

    fun getMusicAlbum(albumId:Long) = database.getMusicAccess().getMusicAlbum(albumId)

    fun getMusicArtist(artistId:Long) = database.getMusicAccess().getMusicArtist(artistId)

    fun getAlbums() = database.getAlbumAccess().getAlbums()

    fun getAlbum(id:Long) = database.getAlbumAccess().getAlbum(id)

    fun getArtist() = database.getArtistAccess().getArtist()

    fun getArtist(id:Long) = database.getArtistAccess().getArtist(id)

    fun getCategories() = database.getCategoryAccess().getCategories()

    fun getMusicIds(categoryId:Long) = database.getCategoryAccess().getMusicIds(categoryId)

    fun getPlaylist() = database.getPlaylistAccess().getPlaylist()

    fun getMusicIds(playlistId:Int) = database.getPlaylistAccess().getMusicIds(playlistId)

    suspend fun addMusic(music: Music):Boolean{
        val task = scope.async {
            val affectedRows = database.getMusicAccess().addMusic(music)
            affectedRows > 0
        }
        return task.await()
    }

    suspend fun addMusic(music:List<Music>):Boolean{
        val task = scope.async {
            val affectedRows = database.getMusicAccess().addMusic(music)
            affectedRows > 0
        }
        return task.await()
    }

    suspend fun removeMusic(music: Music):Boolean{
        val task = scope.async {
            val affectedRows = database.getMusicAccess().removeMusic(music)
            affectedRows > 0
        }
        return task.await()
    }

    suspend fun removeMusic(music: List<Music>):Boolean{
        val task = scope.async {
            val affectedRows = database.getMusicAccess().removeMusic(music)
            affectedRows > 0
        }
        return task.await()
    }

    suspend fun addAlbums(albums: List<Album>):Boolean{
        val task = scope.async {
            val affectedRows = database.getAlbumAccess().addAlbums(albums)
            affectedRows > 0
        }
        return task.await()
    }

    suspend fun addAlbum(album: Album):Boolean{
        val task = scope.async {
            val affectedRows = database.getAlbumAccess().addAlbum(album)
            affectedRows > 0
        }
        return task.await()
    }

    suspend fun addArtist(artist: Artist):Boolean{
        val task = scope.async {
            val affectedRows = database.getArtistAccess().addArtist(artist)
            affectedRows > 0
        }
        return task.await()
    }

    suspend fun addArtist(artists: List<Artist>):Boolean{
        val task = scope.async {
            val affectedRows = database.getArtistAccess().addArtist(artists)
            affectedRows > 0
        }
        return task.await()
    }

    suspend fun addCategory(categories: List<Category>):Boolean{
        val task = scope.async {
            val affectedRows = database.getCategoryAccess().addCategory(categories)
            affectedRows > 0
        }
        return task.await()
    }

    suspend fun addCategory(category: Category):Boolean{
        val task = scope.async {
            val affectedRows = database.getCategoryAccess().addCategory(category)
            affectedRows > 0
        }
        return task.await()
    }

    suspend fun addToBridge(bridge: CategoryBridge):Boolean {
        val task = scope.async {
            val affectedRows = database.getCategoryAccess().addToBridge(bridge)
            affectedRows > 0
        }
        return task.await()
    }

    suspend fun addToBridge(bridge: List<CategoryBridge>):Boolean{
        val task = scope.async {
            val affectedRows = database.getCategoryAccess().addToBridge(bridge)
            affectedRows > 0
        }
        return task.await()
    }

    suspend fun removeAlbum(albums:List<Album>):Boolean{
        val task = scope.async {
            val affectedRows = database.getAlbumAccess().removeAlbum(albums)
            affectedRows > 0
        }
        return task.await()
    }

    suspend fun removeAlbum(album: Album):Boolean{
        val task = scope.async {
            val affectedRows = database.getAlbumAccess().removeAlbum(album)
            affectedRows > 0
        }
        return task.await()
    }

    suspend fun removeArtist(artist: Artist):Boolean{
        val task = scope.async {
            val affectedRows = database.getArtistAccess().removeArtist(artist)
            affectedRows > 0
        }
        return task.await()
    }

    suspend fun removeArtist(artists: List<Artist>):Boolean{
        val task = scope.async {
            val affectedRows = database.getArtistAccess().removeArtist(artists)
            affectedRows > 0
        }
        return task.await()
    }

    suspend fun removeCategory(category: Category):Boolean{
        val task = scope.async {
            val affectedRows = database.getCategoryAccess().removeCategory(category)
            affectedRows > 0
        }
        return task.await()
    }

    suspend fun removeCategory(categories: List<Category>):Boolean{
        val task = scope.async {
            val affectedRows = database.getCategoryAccess().removeCategory(categories)
            affectedRows > 0
        }
        return task.await()
    }

    suspend fun removeBridge(bridge: CategoryBridge):Boolean{
        val task = scope.async {
            val affectedRows = database.getCategoryAccess().removeBridge(bridge)
            affectedRows > 0
        }
        return task.await()
    }

    suspend fun removeBridge(bridge: List<CategoryBridge>):Boolean{
        val task = scope.async {
            val affectedRows = database.getCategoryAccess().removeBridge(bridge)
            affectedRows > 0
        }
        return task.await()
    }
    

    /**
     * initialize the database.
     * Not sure if i should allow one thread executing inside this block at a time
     * @param context
     * @return true when completed, might be helpful when dealing with the loading thingie/view?/prgressbar?. fuck it
     * */
    suspend fun refresh(context: Context,
                        ):Boolean{
        val task = scope.async {
            val music = FromDevice.getMusic(context)
            val album = FromDevice.getAlbums(context)
            val artist = FromDevice.getArtists(context)
            val category = FromDevice.getGenres(context)
            val bridge = FromDevice.getCategoryBridge(context,category)
            resetDatabase()
            addMusic(music)
            addAlbums(album)
            addArtist(artist)
            addCategory(category)
            addToBridge(bridge)
            true
        }
        return task.await()
    }

    /**
     * Clear all data from the database
     * */
    private fun resetDatabase(){
        database.getMusicAccess().clear()
        database.getAlbumAccess().clear()
        database.getArtistAccess().clear()
        database.getCategoryAccess().clear()
        database.getCategoryAccess().clearBridge()
    }
}
