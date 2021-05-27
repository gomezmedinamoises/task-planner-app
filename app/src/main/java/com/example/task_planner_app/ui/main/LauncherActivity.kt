package com.example.task_planner_app.ui.main

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.task_planner_app.storage.Storage
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LauncherActivity : AppCompatActivity() {

    @Inject
    lateinit var storage: Storage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (storage.getToken().isNotEmpty()) {
            startMainActivity()
        } else {
            startLoginActivity()
        }
        finish()
    }

    private fun startLoginActivity() {
        val intentLogin = Intent(this, LoginActivity::class.java)
        intentLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intentLogin)
    }

    private fun startMainActivity() {
        val intentMain = Intent(this, MainActivity::class.java)
        intentMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intentMain)
    }
}