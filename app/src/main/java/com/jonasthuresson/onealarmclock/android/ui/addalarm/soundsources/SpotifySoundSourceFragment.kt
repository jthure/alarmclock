package com.jonasthuresson.onealarmclock.android.ui.addalarm.soundsources

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonasthuresson.onealarmclock.R
import com.jonasthuresson.onealarmclock.android.ui.BaseFragment
import com.jonasthuresson.onealarmclock.android.ui.triggeredalarm.TriggeredAlarmViewModel
import kotlinx.android.synthetic.main.spotify_sound_source_fragment.*

class SpotifySoundSourceFragment : BaseFragment() {
    private lateinit var viewModel: SpotifySoundSourceViewModel
    private lateinit var adapter: SpotifySoundSourceSearchResultListAdapter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel(SpotifySoundSourceViewModel::class.java)
        viewModel.searchText = "test"

        adapter = SpotifySoundSourceSearchResultListAdapter(::onSearchReusltItemClick)
        search_result_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = adapter
        }


    }

    override fun layout(): Int = R.layout.spotify_sound_source_fragment

    private fun onSearchReusltItemClick(){
        Toast.makeText(context, "Item clicked!", Toast.LENGTH_LONG).show()
    }
}