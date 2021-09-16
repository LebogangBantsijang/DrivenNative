package com.lebogang.driven.device.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class PlaylistBridge(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val playlistId:Int,
    val musicId:Long) {
}