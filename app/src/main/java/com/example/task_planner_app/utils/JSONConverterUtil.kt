package com.example.task_planner_app.utils

import com.google.gson.Gson
import java.lang.reflect.Type


object JSONConverterUtil {

    fun fromObjectToJson(value: Any): String {
        return Gson().toJson(value)
    }

    fun <T> fromJsonToObject(json: String, resultClass: Class<T>): T {
        return Gson().fromJson(json, resultClass as Type)
    }

}