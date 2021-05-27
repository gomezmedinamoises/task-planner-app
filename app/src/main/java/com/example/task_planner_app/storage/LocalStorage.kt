package com.example.task_planner_app.storage

import android.content.Context
import com.example.task_planner_app.R
import com.example.task_planner_app.utils.SHARED_PREFERENCES_FILE_NAME
import com.example.task_planner_app.utils.TOKEN_KEY
import dagger.hilt.android.qualifiers.ApplicationContext

class LocalStorage(context: Context) : Storage {

    private val sharedPref = context.getSharedPreferences(
        SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE
    )

    override fun saveToken(token: String) {
        sharedPref.edit().putString(TOKEN_KEY, token).apply()
    }

    override fun getToken(): String {
        return sharedPref.getString(TOKEN_KEY, "")!!
    }

    override fun clear() {
        return sharedPref.edit()
            .remove(TOKEN_KEY)
            .apply()
    }
}