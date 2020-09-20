package com.bayera.xlog.pcremote.RemoteActivity

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Network constructor(context: Context) : ServerApi {
    companion object {
        @Volatile
        private var INSTANCE: Network? = null
        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Network(context).also {
                    INSTANCE = it
                }
            }
    }

    val requestQueue: RequestQueue by lazy {
        // applicationContext is key, it keeps you from leaking the
        // Activity or BroadcastReceiver if someone passes one in.
        Volley.newRequestQueue(context.applicationContext)
    }
    fun <T> addToRequestQueue(req: Request<T>) {
        requestQueue.add(req)
    }

    override var address: String = ""

    override fun sendPost(json: JSONObject) {
        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, address, json,
            { response -> Log.d("NETWORK", response.toString()) },
            { error -> Log.d("NETWORK", error.toString()) })
        addToRequestQueue(jsonObjectRequest)
    }

}
