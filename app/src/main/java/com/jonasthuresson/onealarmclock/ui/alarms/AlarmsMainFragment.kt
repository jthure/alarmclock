package com.jonasthuresson.onealarmclock.ui.alarms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.jonasthuresson.onealarmclock.R
import kotlinx.android.synthetic.main.alarms_fragment.*
import kotlinx.android.synthetic.main.alarms_fragment.view.*

class AlarmsMainFragment : Fragment() {

    companion object {
        fun newInstance() = AlarmsMainFragment()
    }

    private val viewModel by viewModels<AlarmsViewModel>()
    private lateinit var alarmsAdapter: AlarmsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        alarmsAdapter = AlarmsAdapter()
        viewModel.alarms.observe(viewLifecycleOwner){ alarms ->
            alarmsAdapter.setData(alarms)
        }
        val v = inflater.inflate(R.layout.alarms_fragment, container, false)
        v.alarms_list.adapter = alarmsAdapter
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.fetchAlarms()
    }

}
