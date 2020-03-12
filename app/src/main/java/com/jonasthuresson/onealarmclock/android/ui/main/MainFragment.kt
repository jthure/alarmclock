package com.jonasthuresson.onealarmclock.android.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jonasthuresson.onealarmclock.R
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    //private lateinit var viewModel: AlarmsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(AlarmsViewModel::class.java)
        setupAddAlarmButton()
    }

    private fun setupAddAlarmButton(){
        button_new_alarm.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToAddAlarmFragment())
        }
    }

}
