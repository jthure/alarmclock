package com.jonasthuresson.onealarmclock.android.ui.addalarm.soundsources

import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonasthuresson.onealarmclock.R
import com.jonasthuresson.onealarmclock.android.ui.BaseFragment
import com.jonasthuresson.onealarmclock.android.ui.addalarm.AddAlarmViewModel
import com.jonasthuresson.onealarmclock.model.AlarmSound
import kotlinx.android.synthetic.main.spotify_sound_source_fragment.*

class SpotifySoundSourceFragment : BaseFragment() {
    private lateinit var viewModel: SpotifySoundSourceViewModel
    private lateinit var addAlarmViewModel: AddAlarmViewModel
    private lateinit var adapter: SpotifySoundSourceSearchResultListAdapter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel(SpotifySoundSourceViewModel::class.java)
        addAlarmViewModel = getViewModel(AddAlarmViewModel::class.java)

        outlinedTextField.editText?.doAfterTextChanged { text ->
            viewModel.searchText = text.toString()
        }
        adapter = SpotifySoundSourceSearchResultListAdapter(::onSearchReusltItemClick)
        search_result_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@SpotifySoundSourceFragment.context)
            adapter = this@SpotifySoundSourceFragment.adapter
        }

        viewModel.searchResult.observe(viewLifecycleOwner, Observer { searchResult ->
            adapter.setData(searchResult)
        })


    }

    override fun layout(): Int = R.layout.spotify_sound_source_fragment

    private fun onSearchReusltItemClick(selectedSound: AlarmSound) {
        Toast.makeText(context, "Item clicked! $selectedSound", Toast.LENGTH_LONG).show()
        addAlarmViewModel.setAlarmSound(selectedSound)
        findNavController().navigate(SpotifySoundSourceFragmentDirections.actionSpotifySoundSourceFragmentToAddAlarmFragment())
    }
}