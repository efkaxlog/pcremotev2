package com.bayera.xlog.pcremote.RemoteActivity

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.bayera.xlog.pcremote.R
import com.bayera.xlog.pcremote.RemoteActivity.media.MediaFragment
import com.bayera.xlog.pcremote.RemoteActivity.mouse.MouseFragment
import com.bayera.xlog.pcremote.RemoteActivity.power.PowerFragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class RemoteActivity() : AppCompatActivity(), RemoteContract.View {

    lateinit var presenter: RemoteContract.Presenter
    lateinit var tabAdapter: TabAdapter
    lateinit var serverApi: ServerApi
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        serverApi = Network.getInstance(applicationContext)
        serverApi.address = "http://192.168.0.14:8000"

        tabAdapter = TabAdapter(this)
        tabAdapter.addFragment(MouseFragment(serverApi), "Mouse")
        tabAdapter.addFragment(MediaFragment(serverApi), "Media")
        tabAdapter.addFragment(PowerFragment(serverApi), "Power")
        vp_remote.adapter = tabAdapter

        TabLayoutMediator(tl_remote, vp_remote) { tab, position ->
            tab.text = tabAdapter.getPageTitle(position)
            vp_remote.setCurrentItem(tab.position, true)
        }.attach()

        setSupportActionBar(toolbar)

        toggle = ActionBarDrawerToggle(this, layout_drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        layout_drawer.addDrawerListener(toggle)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        presenter = MainPresenter(serverApi,this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun onTaskButtonClick(view: View) {
        presenter.sendTask(view.tag.toString())
    }

    fun sendPostRequest(json: JSONObject) {
        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, "http://192.168.0.14:8000", json,
            { response -> Log.d("", response.toString()) },
            { error -> Log.d("DebugMainActivity", error.toString()) })
        Network.getInstance(applicationContext).addToRequestQueue(jsonObjectRequest)
    }



    override fun showMessage(message: String) {
        Snackbar.make(layout_remote_activity, message, Snackbar.LENGTH_LONG).show()
    }
}