package com.bayera.xlog.pcremote.RemoteActivity

import org.json.JSONObject

interface RemoteContract {
    interface View {
        fun showMessage(message: String)
    }

    interface Presenter {
        fun sendTask(task: String)

    }
}