package com.bayera.xlog.pcremote.RemoteActivity.media

import com.bayera.xlog.pcremote.RemoteActivity.ServerApi
import com.bayera.xlog.pcremote.common.makeJson
import org.json.JSONObject

class MediaPresenter(val view: MediaContract.View, val serverApi: ServerApi) : MediaContract.Presenter {

    override fun onTaskButtonPressed(task: String) {
        // json = makeJson(task)
        // networkApi.sendPost(json)
        val json: JSONObject? = try { makeJson(task) } catch (e: Exception) { null }

        if (json != null) {
            serverApi.sendPost(json)
            view.showMessage("Sent to server: $json")
        } else {
            view.showMessage(""""$task invalid"""")
        }
    }


}
