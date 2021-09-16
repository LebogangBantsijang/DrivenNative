package com.lebogang.driven

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.lebogang.driven.device.database.dao.*
import com.lebogang.driven.device.database.models.*
/**
 * Make a database to allow paging
 * this will help with memory
 * */
@Database(entities = [
    Album::class,
    Artist::class,
    Music::class,
    Category::class,
    CategoryBridge::class,
    Playlist::class,
    PlaylistBridge::class],
    version = 100)
abstract class DrivenDatabase: RoomDatabase() {
    abstract fun getMusicAccess():MusicAccess
    abstract fun getAlbumAccess():AlbumAccess
    abstract fun getArtistAccess():ArtistAccess
    abstract fun getCategoryAccess():CategoryAccess
    abstract fun PlaylistAccess():PlaylistAccess

    /**
     * Honestly I am not sure it this is gonna work but anyway.
     * */
    companion object Helper{
        //don't remember but fuck it
        private var database:DrivenDatabase? = null
        fun getDatabase(context: Context):DrivenDatabase{
            return database?: synchronized(this){
                val newDatabase = Room.databaseBuilder(context,DrivenDatabase::class.java,"Driven")
                    //Destroy everything lol
                    .fallbackToDestructiveMigration()
                    .build()
                database = newDatabase
                newDatabase
            }
        }
    }

}