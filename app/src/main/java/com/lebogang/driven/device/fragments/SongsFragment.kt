package com.lebogang.driven.device.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lebogang.driven.R

class SongsFragment : Fragment() {

    override fun onCreateView(i: LayoutInflater, c: ViewGroup?, s: Bundle?): View? {
        return i.inflate(R.layout.fragment_general, c, false)
    }

}