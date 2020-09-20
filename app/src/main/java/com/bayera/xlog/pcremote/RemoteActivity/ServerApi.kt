package com.bayera.xlog.pcremote.RemoteActivity

import org.json.JSONObject

interface ServerApi {
    var address: String
    fun sendPost(json: JSONObject)

}