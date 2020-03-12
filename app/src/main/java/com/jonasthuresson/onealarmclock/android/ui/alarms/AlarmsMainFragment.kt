package com.jonasthuresson.onealarmclock.android.ui.alarms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jonasthuresson.onealarmclock.R
import kotlinx.android.synthetic.main.alarms_fragment.view.*

class AlarmsMainFragment : Fragment() {

    companion object {
        fun newInstance() = AlarmsMainFragment()
    }

    private val viewModel: AlarmsViewModel by activityViewModels()
    private lateinit var alarmsAdapter: AlarmsAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        alarmsAdapter = AlarmsAdapter()
        viewManager = LinearLayoutManager(context)
        viewModel.alarms.observe(viewLifecycleOwner) { alarms ->
            alarmsAdapter.setData(alarms)
        }

        val v = inflater.inflate(R.layout.alarms_fragment, container, false)
        v.alarms_list.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = alarmsAdapter
        }
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.fetchAlarms()
    }

}
