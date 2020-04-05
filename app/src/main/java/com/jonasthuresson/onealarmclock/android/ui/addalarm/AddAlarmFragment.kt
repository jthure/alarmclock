package com.jonasthuresson.onealarmclock.android.ui.addalarm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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
        viewModel.existingAlarm.value?.let { alarm ->
            time_picker.hour = alarm.time.hour
            time_picker.minute = alarm.time.minute
            repeat_button_monday.isChecked = alarm.isDaySetToRepeat(DAYS.MONDAY)
            repeat_button_tuesday.isChecked = alarm.isDaySetToRepeat(DAYS.TUESDAY)
            repeat_button_wednesday.isChecked = alarm.isDaySetToRepeat(DAYS.WEDNESDAY)
            snooze_text_view.text = alarm.getSnoozeDurationInMinutes().toString()
        }


        time_picker.setOnTimeChangedListener { view, hourOfDay, minute ->
            viewModel.setAlarmTime(hourOfDay, minute)
        }
        repeat_button_group.addOnButtonCheckedListener { group, checkedId, isChecked ->
            val day = when (checkedId) {
                R.id.repeat_button_monday -> DAYS.MONDAY
                R.id.repeat_button_tuesday -> DAYS.TUESDAY
                R.id.repeat_button_wednesday -> DAYS.WEDNESDAY
                else -> null
            }
            day?.let { viewModel.setAlarmDay(day, isChecked) }
        }
        plus_button.setOnClickListener { viewModel.increaseSnooze() }
        minus_button.setOnClickListener { viewModel.decreaseSnooze() }
        viewModel.existingAlarm.observe(viewLifecycleOwner, Observer { alarm ->
            snooze_text_view.text = alarm.getSnoozeDurationInMinutes().toString()
        })
        sound_source_selector.setAdapter(SoundSourceAdapter(context!!,
            listOf(SoundSource("Spotify", AddAlarmFragmentDirections.actionAddAlarmFragmentToSpotifySoundSourceFragment())),
            R.layout.sound_source_list_item_view, findNavController()))
        button_save.setOnClickListener {
            viewModel.saveAlarm()
            findNavController().navigate(AddAlarmFragmentDirections.actionAddAlarmFragmentToMainFragment())
        }
    }

    override fun layout() = R.layout.fragment_add_alarm
}
