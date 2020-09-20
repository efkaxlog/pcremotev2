package com.bayera.xlog.pcremote.RemoteActivity.mouse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bayera.xlog.pcremote.R
import com.bayera.xlog.pcremote.RemoteActivity.ServerApi
import kotlinx.android.synthetic.main.fragment_remote_mouse.*
import kotlinx.android.synthetic.main.fragment_remote_mouse.view.*

class MouseFragment(val serverApi: ServerApi) : Fragment(), MouseContract.View {

    lateinit var presenter: MousePresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?) : View = inflater.inflate(R.layout.fragment_remote_mouse, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.mousepad.fragment = this
        view.btn_left_click.setOnClickListener { mouseClick("left") }
        view.btn_right_click.setOnClickListener { mouseClick("right") }
        presenter = MousePresenter(this, serverApi)
    }

    fun mousePadMovement(x: Float, y: Float) =
        presenter.moveMouse(x, y)

    fun mouseClick(button: String) =
        presenter.clickMouse(button)

}