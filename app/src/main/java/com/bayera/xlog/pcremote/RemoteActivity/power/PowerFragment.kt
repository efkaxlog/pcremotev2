package com.bayera.xlog.pcremote.RemoteActivity.power

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bayera.xlog.pcremote.R
import com.bayera.xlog.pcremote.RemoteActivity.ServerApi
import com.bayera.xlog.pcremote.RemoteActivity.media.MediaPresenter
import kotlinx.android.synthetic.main.fragment_remote_power.*

class PowerFragment(val serverApi: ServerApi) : Fragment(), PowerContract {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_remote_power, container, false)
}
