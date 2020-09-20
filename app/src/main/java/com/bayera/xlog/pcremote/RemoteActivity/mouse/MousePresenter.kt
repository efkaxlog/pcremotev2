package com.bayera.xlog.pcremote.RemoteActivity.mouse

import com.bayera.xlog.pcremote.RemoteActivity.ServerApi
import com.bayera.xlog.pcremote.common.makeJson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import java.util.concurrent.Executors

class MousePresenter(val view: MouseContract.View, val serverApi: ServerApi) : MouseContract.Presenter {

    var sensitivity = 2
    var timeBetweenMouseMoveRequests = 100 // max 10 requests a second
    var lastMouseMovedTime = System.currentTimeMillis()

    override fun moveMouse(x: Float, y: Float) {
        var currentTime = System.currentTimeMillis()
        if (currentTime - lastMouseMovedTime >= timeBetweenMouseMoveRequests) {
            var movementX = sensitivity * x
            var movementY = sensitivity * y
            var json = makeJson("mouse_move_$movementX,$movementY")
            serverApi.sendPost(json)
            lastMouseMovedTime = currentTime
        }
    }
    override fun clickMouse(button: String) {
        var json = makeJson("mouse_click_$button")
        serverApi.sendPost(json)
    }
}
