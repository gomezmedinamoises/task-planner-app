package com.example.task_planner_app.analytics

import android.util.Log

class FlurryAnalyticsReporter : AnalyticsReporter {

    override fun reportEvent(eventName: String, data: Map<String, String>) {
        Log.d("Developer", "Flurry report event: $eventName")
    }
}