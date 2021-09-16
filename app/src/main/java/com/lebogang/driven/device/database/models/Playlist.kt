package com.lebogang.driven.device.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Playlist(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val title:String,
    val cover:String = "Not Image") {
}