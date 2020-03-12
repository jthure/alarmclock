package com.jonasthuresson.onealarmclock.android.ui.addalarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import com.jonasthuresson.onealarmclock.R
import com.jonasthuresson.onealarmclock.android.ui.alarms.AlarmsViewModel
import kotlinx.android.synthetic.main.fragment_add_alarm.*


class AddAlarmFragment : Fragment() {

    private val viewModel: AddAlarmViewModel by viewModels()
    private val alarmsViewModel: AlarmsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_add_alarm, container, false)
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        time_picker.setOnTimeChangedListener { view, hourOfDay, minute ->
            viewModel.setTime(
                hourOfDay,
                minute
            )
        }
        button_save.setOnClickListener {
            alarmsViewModel.saveAlarm(viewModel.getTime())
            findNavController().navigate(AddAlarmFragmentDirections.actionAddAlarmFragmentToMainFragment())
        }
    }


}
