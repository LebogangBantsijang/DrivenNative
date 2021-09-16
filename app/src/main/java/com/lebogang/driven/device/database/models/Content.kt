package com.lebogang.driven.device.database.models

import android.os.Parcelable
import androidx.room.Ignore

abstract class Content(@Ignore val type:TYPE):Parcelable {

    enum class TYPE{
        ALBUM,ARTIST,CATEGORY
    }
}