package com.bayera.xlog.pcremote.common

import android.util.Log
import org.json.JSONObject

fun makeJson(str: String) : JSONObject {
    val splitted = str.split("_")
    val json = JSONObject()
    json.put("task_type", splitted[0])
    json.put("task", splitted[1])
    json.put("args", splitted[2])
    return json
}