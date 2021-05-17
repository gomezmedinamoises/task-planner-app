package com.example.task_planner_app.analytics

interface AnalyticsReporter {

    fun reportEvent(eventName: String, data: Map<String, String>)

}