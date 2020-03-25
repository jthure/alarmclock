package com.jonasthuresson.onealarmclock.android.ui.alarms

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jonasthuresson.onealarmclock.R
import com.jonasthuresson.onealarmclock.android.ui.BaseFragment
import com.jonasthuresson.onealarmclock.android.ui.addalarm.AddAlarmViewModel
import com.jonasthuresson.onealarmclock.db.Alarm
import com.spotify.android.appremote.api.ConnectionParams
import com.spotify.android.appremote.api.Connector
import com.spotify.android.appremote.api.SpotifyAppRemote
import kotlinx.android.synthetic.main.alarms_fragment.*
import kotlinx.android.synthetic.main.main_fragment.button_new_alarm


class AlarmsFragment : BaseFragment() {

    private lateinit var viewModel: AlarmsViewModel
    private lateinit var alarmsAdapter: AlarmsAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var addAlarmViewModel: AddAlarmViewModel

    private val CLIENT_ID = "7310d321ef974502973b1808e37bbc14"
    private val REDIRECT_URI = "http://onealarmclock.jonasthuresson.com/spotify/auth"
    private var mSpotifyAppRemote: SpotifyAppRemote? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel(AlarmsViewModel::class.java)
        addAlarmViewModel = getViewModel(AddAlarmViewModel::class.java)
        alarmsAdapter = AlarmsAdapter(::onItemClick)
        viewManager = LinearLayoutManager(context)
        viewModel.alarms.observe(viewLifecycleOwner) { alarms ->
            alarmsAdapter.setData(alarms)
        }
        viewModel.spotifyManagerLiveData.observe(viewLifecycleOwner) {
            Log.d("AlarmsFragment", "Got SpotifyManager")
        }
        alarms_list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = alarmsAdapter
        }
        setupAddAlarmButton()
        viewModel.fetchAlarms()
        authSpotify()
    }

    private fun onItemClick(alarm: Alarm) {
        addAlarmViewModel.existingAlarm = alarm
        findNavController().navigate(AlarmsFragmentDirections.actionMainFragmentToAddAlarmFragment())
    }
    private fun setupAddAlarmButton() {
        button_new_alarm.setOnClickListener {
            addAlarmViewModel.resetAlarm()
            findNavController().navigate(AlarmsFragmentDirections.actionMainFragmentToAddAlarmFragment())
        }
    }

    private fun authSpotify() {
    }

    override fun layout() = R.layout.alarms_fragment

}
