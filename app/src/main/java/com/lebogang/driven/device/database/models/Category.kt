package com.lebogang.driven.device.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * We'll use glide and some other library for images.
 * - Find a useful library
 * - Go through the glide docs and see how to implement it. {Don't be lazy!!!}
 * */

@Parcelize
@Entity
class Category(
    @PrimaryKey val id:Long,
    val title:String):Content(TYPE.CATEGORY)
