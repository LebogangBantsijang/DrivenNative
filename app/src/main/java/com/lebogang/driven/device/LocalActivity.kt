package com.lebogang.driven.device

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.lebogang.driven.R
import com.lebogang.driven.databinding.ActivityLocalBinding

class LocalActivity : AppCompatActivity() {
    private val bind:ActivityLocalBinding by lazy { ActivityLocalBinding.inflate(layoutInflater) }
    private lateinit var permission:ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        checkPermission()
    }

    /**
     * Check if we have permission, if we do then proceed else request permission
     * */
    private fun checkPermission(){
        //permissions
        permission = registerForActivityResult(RequestPermission()){
            if (it) getData() else onBackPressed()
        }
        //check
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_DENIED)
            permission.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
        else
            getData()
    }

    //I'll fix you later
    private fun getData(){

    }
}