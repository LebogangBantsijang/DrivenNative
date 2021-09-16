package com.lebogang.driven.device.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This table is used to match the music with the correct genre/category
 * */
@Entity
class CategoryBridge(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val categoryId:Long,
    val musicId:Long) {
}