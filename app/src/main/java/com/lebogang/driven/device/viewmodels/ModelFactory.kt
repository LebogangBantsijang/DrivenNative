package com.lebogang.driven.device.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class ModelFactory:ViewModelProvider.Factory {

    fun getMusicViewModel():ViewModel = create(MusicViewModel::class.java)

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MusicViewModel::class.java) -> MusicViewModel() as T
            else -> throw IllegalArgumentException()
        }
    }
}