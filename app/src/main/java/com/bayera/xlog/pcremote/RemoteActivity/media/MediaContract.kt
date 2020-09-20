package com.bayera.xlog.pcremote.RemoteActivity.media

interface MediaContract {
    interface View {
        fun showMessage(message: String)
    }

    interface Presenter {
        fun onTaskButtonPressed(task: String)
    }
}