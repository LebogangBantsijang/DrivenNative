package com.lebogang.driven.device.database

import android.annotation.SuppressLint
import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore.Audio
import com.lebogang.driven.device.database.models.*

object FromDevice {
    private val MUSIC_PROJECTION = arrayOf(Audio.Media._ID,Audio.Media.ALBUM_ID,Audio.Media.ARTIST_ID,
        Audio.Media.TITLE,Audio.Media.ALBUM,Audio.Media.ARTIST,Audio.Media.DURATION,
        Audio.Media.SIZE,Audio.Media.DATE_MODIFIED)
    private val ALBUM_PROJECTION = arrayOf(Audio.Albums._ID,Audio.Albums.ALBUM,Audio.Albums.ARTIST)
    private val ARTIST_PROJECTION = arrayOf(Audio.Artists._ID,Audio.Artists.ARTIST)
    private val GENRE_PROJECTION = arrayOf(Audio.Genres._ID,Audio.Genres.NAME)
    private val GENRE_BRIDGE_PROJECTION = arrayOf(Audio.Genres.Members.AUDIO_ID)
    private val MUSIC_URI = Audio.Media.EXTERNAL_CONTENT_URI!!
    private val ALBUM_URI = Audio.Albums.EXTERNAL_CONTENT_URI!!
    private val ARTIST_URI = Audio.Artists.EXTERNAL_CONTENT_URI!!
    private val GENRE_URI = Audio.Genres.EXTERNAL_CONTENT_URI!!

    private fun getMemberUri(id:Long) = Audio.Genres.Members.getContentUri("external",id)!!

    /**
     * Not sure if it's gonna work with other api levels but hey, do now, fix later.
     * */
    private fun getAlbumArtUri(id:Long):Uri = ContentUris.withAppendedId(
        Uri.parse("content://media/external/audio/albumart"), id)

    /**
     * Get music from device
     * @param context
     * @return list of music
     * */
    @SuppressLint("Range")
    fun getMusic(context: Context):List<Music>{
        val list = mutableListOf<Music>()
        val cursor = context.contentResolver.query(MUSIC_URI,MUSIC_PROJECTION,
            Audio.Media.IS_MUSIC,null,null)
        cursor?.let {
            if (it.moveToFirst()){
                do {
                    val id = it.getLong(it.getColumnIndex(Audio.Media._ID))
                    val albumId = it.getLong(it.getColumnIndex(Audio.Media.ALBUM_ID))
                    val artistId = it.getLong(it.getColumnIndex(Audio.Media.ARTIST_ID))
                    val title = it.getString(it.getColumnIndex(Audio.Media.TITLE))
                    val album = it.getString(it.getColumnIndex(Audio.Media.ALBUM))
                    val artist = it.getString(it.getColumnIndex(Audio.Media.ARTIST))
                    val duration = it.getLong(it.getColumnIndex(Audio.Media.DURATION))
                    val size = it.getLong(it.getColumnIndex(Audio.Media.SIZE))
                    val date = it.getLong(it.getColumnIndex(Audio.Media.DATE_MODIFIED))
                    val uri = ContentUris.withAppendedId(MUSIC_URI,id)
                    val cover = getAlbumArtUri(albumId)
                    val music = Music(id,albumId,artistId,title,album,artist, duration, size, date, cover, uri)
                    list.add(music)
                }while (it.moveToNext())
            }
        }
        cursor?.close()
        return list
    }

    /**
     * Get albums from device
     * @param context
     * @return list of album
     * */
    fun getAlbums(context: Context):List<Album>{
        val list = mutableListOf<Album>()
        val cursor = context.contentResolver.query(ALBUM_URI,ALBUM_PROJECTION
            ,null,null,null)
        cursor?.let {
            if (it.moveToFirst()){
                do {
                    val id = it.getLong(it.getColumnIndex(Audio.Albums.ALBUM_ID))
                    val title = it.getString(it.getColumnIndex(Audio.Albums.ALBUM))
                    val artist = it.getString(it.getColumnIndex(Audio.Albums.ARTIST))
                    val cover = getAlbumArtUri(id)
                    val album = Album(id, title, artist, cover)
                    list.add(album)
                }while (it.moveToNext())
            }
        }
        cursor?.close()
        return list
    }

    /**
     * Get artist from device
     * @param context
     * @return list of artist
     * */
    fun getArtists(context: Context):List<Artist>{
        val list = mutableListOf<Artist>()
        val cursor = context.contentResolver.query(ARTIST_URI,ARTIST_PROJECTION
            ,null,null,null)
        cursor?.let {
            if (it.moveToFirst()){
                do {
                    val id = it.getLong(it.getColumnIndex(Audio.Artists._ID))
                    val title = it.getString(it.getColumnIndex(Audio.Artists.ARTIST))
                    val artist = Artist(id, title)
                    list.add(artist)
                }while (it.moveToNext())
            }
        }
        cursor?.close()
        return list
    }

    /**
     * Get genres from device
     * @param context
     * @return list of genres
     * */
    fun getGenres(context: Context):List<Category>{
        val list = mutableListOf<Category>()
        val cursor = context.contentResolver.query(GENRE_URI,GENRE_PROJECTION
            ,null,null,null)
        cursor?.let {
            if (it.moveToFirst()){
                do {
                    val id = it.getLong(it.getColumnIndex(Audio.Genres._ID))
                    val title = it.getString(it.getColumnIndex(Audio.Genres.NAME))
                    val category = Category(id,title)
                    list.add(category)
                }while (it.moveToNext())
            }
        }
        cursor?.close()
        return list
    }

    /**
     * Create Genre bridge table instances? fuck it
     * @param context
     * @param categories: used to get the uri
     * @return list of music
     * */
    fun getCategoryBridge(context: Context,categories:List<Category>):List<CategoryBridge>{
        val list = mutableListOf<CategoryBridge>()
        categories.forEach {category->
            val uri = getMemberUri(category.id)
            val cursor = context.contentResolver.query(uri,GENRE_BRIDGE_PROJECTION
                ,null,null,null)
            cursor?.let {
                if (it.moveToFirst()){
                    do {
                        val audioId = it.getLong(it.getColumnIndex(Audio.Genres.Members.AUDIO_ID))
                        val categoryBridge = CategoryBridge(0,category.id,audioId)
                        list.add(categoryBridge)
                    }while (it.moveToNext())
                }
            }
            cursor?.close()
        }
        return list
    }
    
}
