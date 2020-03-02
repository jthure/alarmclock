package com.jonasthuresson.onealarmclock.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jonasthuresson.onealarmclock.R
import com.jonasthuresson.onealarmclock.ui.alarms.AlarmsMainFragment
import com.jonasthuresson.onealarmclock.ui.alarms.AlarmsViewModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = AlarmsMainFragment()
    }

    private lateinit var viewModel: AlarmsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AlarmsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
