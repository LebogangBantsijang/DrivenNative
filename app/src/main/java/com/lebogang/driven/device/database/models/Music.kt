package com.lebogang.driven.device.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Music(
    @PrimaryKey val id:Long,
    val albumId:Long,
    val artistId:Long,
    val title:String,
    val album:String,
    val artist:String,
    val duration:Long,
    val size:Long,
    val date:Long,
    val cover:String,
    val uri:String)
