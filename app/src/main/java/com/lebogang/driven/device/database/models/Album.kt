package com.lebogang.driven.device.database.models

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Album (
    @PrimaryKey val id:Long,
    val title:String,
    val artist:String,
    val cover:Uri):Content(TYPE.ALBUM)
