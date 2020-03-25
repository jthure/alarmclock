package com.jonasthuresson.onealarmclock.android.ui.addalarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.jonasthuresson.onealarmclock.R
import com.jonasthuresson.onealarmclock.android.OneAlarmApplication
import com.jonasthuresson.onealarmclock.android.helpers.SystemAlarmManager
import com.jonasthuresson.onealarmclock.android.ui.BaseFragment
import com.jonasthuresson.onealarmclock.android.ui.alarms.AlarmsViewModel
import com.jonasthuresson.onealarmclock.db.DAYS
import kotlinx.android.synthetic.main.fragment_add_alarm.*
import java.time.LocalTime
import javax.inject.Inject


class AddAlarmFragment : BaseFragment() {

    private lateinit var viewModel: AddAlarmViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel(AddAlarmViewModel::class.java)
        time_picker.hour = viewModel.existingAlarm.time.hour
        time_picker.minute = viewModel.existingAlarm.time.minute
        repeat_button_monday.isChecked = viewModel.existingAlarm.isDaySetToRepeat(DAYS.MONDAY)
        repeat_button_tuesday.isChecked = viewModel.existingAlarm.isDaySetToRepeat(DAYS.TUESDAY)
        repeat_button_wednesday.isChecked = viewModel.existingAlarm.isDaySetToRepeat(DAYS.WEDNESDAY)

        time_picker.setOnTimeChangedListener { view, hourOfDay, minute ->
            viewModel.existingAlarm =
                viewModel.existingAlarm.copy(time = LocalTime.of(hourOfDay, minute))
        }
        repeat_button_group.addOnButtonCheckedListener { group, checkedId, isChecked ->
            viewModel.existingAlarm = when(checkedId){
                R.id.repeat_button_monday -> viewModel.existingAlarm.setDay(DAYS.MONDAY, isChecked)
                R.id.repeat_button_tuesday -> viewModel.existingAlarm.setDay(DAYS.TUESDAY, isChecked)
                R.id.repeat_button_wednesday -> viewModel.existingAlarm.setDay(DAYS.WEDNESDAY, isChecked)
                else -> viewModel.existingAlarm
            }
        }
        button_save.setOnClickListener {

            viewModel.saveAlarm(viewModel.existingAlarm)
            findNavController().navigate(AddAlarmFragmentDirections.actionAddAlarmFragmentToMainFragment())
        }
    }
    override fun layout() = R.layout.fragment_add_alarm
}
