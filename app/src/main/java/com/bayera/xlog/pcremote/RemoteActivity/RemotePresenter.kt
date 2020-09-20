package com.bayera.xlog.pcremote.RemoteActivity

import com.bayera.xlog.pcremote.common.makeJson
import org.json.JSONObject

class MainPresenter(val serverApi: ServerApi, val view: RemoteContract.View) : RemoteContract.Presenter {

    override fun sendTask(task: String) {
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