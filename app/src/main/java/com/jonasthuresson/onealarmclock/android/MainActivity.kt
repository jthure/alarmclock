package com.jonasthuresson.onealarmclock.android

import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import com.jonasthuresson.onealarmclock.R
import com.jonasthuresson.onealarmclock.android.helpers.SystemAlarmManager
import com.jonasthuresson.onealarmclock.android.ui.alarms.AlarmsFragmentDirections
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.main_activity.*


class MainActivity : DaggerAppCompatActivity() {

    companion object{
        const val ACTION_ALARM = "com.jonasthuresson.onealarmclock.actions.alarm"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        handleIntent(intent)
        // ATTENTION: This was auto-generated to handle app links.
        // ATTENTION: This was auto-generated to handle app links.
        val appLinkIntent = intent
        val appLinkAction = appLinkIntent.action
        val appLinkData = appLinkIntent.data

    }

    private fun handleIntent(intent: Intent?) {
        if(intent?.action == ACTION_ALARM){
            val id = intent?.getLongExtra(SystemAlarmManager.EXTRA_ALARM_ID, -1)
            nav_host_fragment.findNavController().navigate(AlarmsFragmentDirections.actionMainFragmentToTriggeredAlarm(id))
        }
    }
}
