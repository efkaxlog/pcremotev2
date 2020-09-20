package com.bayera.xlog.pcremote.RemoteActivity.media

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bayera.xlog.pcremote.R
import com.bayera.xlog.pcremote.RemoteActivity.ServerApi
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_remote_media.*

class MediaFragment(val serverApi: ServerApi) : Fragment(), MediaContract.View{

    lateinit var presenter: MediaPresenter
    lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?) : View = inflater.inflate(R.layout.fragment_remote_media, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = MediaPresenter(this, serverApi)
    }

    override fun showMessage(message: String) {
        TODO("Not yet implemented")
    }

}