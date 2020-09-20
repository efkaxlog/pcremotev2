package com.bayera.xlog.pcremote.RemoteActivity.mouse

interface MouseContract {
    interface View {

    }

    interface Presenter {
        fun moveMouse(x: Float, y: Float)
        fun clickMouse(button: String)
    }
}
