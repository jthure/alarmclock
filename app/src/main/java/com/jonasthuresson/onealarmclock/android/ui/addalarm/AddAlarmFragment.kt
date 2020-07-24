package com.jonasthuresson.onealarmclock.android.ui.addalarm

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.jonasthuresson.onealarmclock.R
import com.jonasthuresson.onealarmclock.android.ui.BaseFragment
import com.jonasthuresson.onealarmclock.model.Alarm
import kotlinx.android.synthetic.main.fragment_add_alarm.*


class AddAlarmFragment : BaseFragment() {

    private lateinit var viewModel: AddAlarmViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = getViewModel(AddAlarmViewModel::class.java)
        viewModel.existingAlarm.value?.let { alarm ->
            time_picker.hour = alarm.time.hour
            time_picker.minute = alarm.time.minute
            repeat_button_monday.isChecked = alarm.isDaySetToRepeat(Alarm.DAYS.MONDAY)
            repeat_button_tuesday.isChecked = alarm.isDaySetToRepeat(Alarm.DAYS.TUESDAY)
            repeat_button_wednesday.isChecked = alarm.isDaySetToRepeat(Alarm.DAYS.WEDNESDAY)
            snooze_text_view.text = alarm.getSnoozeDurationInMinutes().toString()
        }


        time_picker.setOnTimeChangedListener { view, hourOfDay, minute ->
            viewModel.setAlarmTime(hourOfDay, minute)
        }
        repeat_button_group.addOnButtonCheckedListener { group, checkedId, isChecked ->
            val day = when (checkedId) {
                R.id.repeat_button_monday -> Alarm.DAYS.MONDAY
                R.id.repeat_button_tuesday -> Alarm.DAYS.TUESDAY
                R.id.repeat_button_wednesday -> Alarm.DAYS.WEDNESDAY
                else -> null
            }
            day?.let { viewModel.setAlarmDay(day, isChecked) }
        }
        plus_button.setOnClickListener { viewModel.increaseSnooze() }
        minus_button.setOnClickListener { viewModel.decreaseSnooze() }
        viewModel.existingAlarm.observe(viewLifecycleOwner, Observer { alarm ->
            snooze_text_view.text = alarm.getSnoozeDurationInMinutes().toString()
            selected_sound.text = alarm.sound.getTitle()
        })
//        sound_source_selector.setAdapter(
//            SoundSourceAdapter(
//                requireContext(),
//                listOf(
//                    SoundSource(
//                        "Spotify",
//                        AddAlarmFragmentDirections.actionAddAlarmFragmentToSpotifySoundSourceFragment()
//                    )
//                ),
//                R.layout.sound_source_list_item_view, findNavController()
//            )
//        )
        button_save.setOnClickListener {
            viewModel.saveAlarm()
            findNavController().navigate(AddAlarmFragmentDirections.actionAddAlarmFragmentToMainFragment())
        }

        selected_sound.setOnClickListener {
            findNavController().navigate(AddAlarmFragmentDirections.actionAddAlarmFragmentToSelectSoundSourceDialogFragment())
        }
    }

    override fun layout() = R.layout.fragment_add_alarm
}
