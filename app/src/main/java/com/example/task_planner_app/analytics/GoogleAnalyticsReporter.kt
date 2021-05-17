package com.example.task_planner_app.analytics

import android.util.Log

class GoogleAnalyticsReporter : AnalyticsReporter {

    override fun reportEvent(eventName: String, data: Map<String, String>) {
        Log.d("Developer", "Google report event: $eventName")
    }
}