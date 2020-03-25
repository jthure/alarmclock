package com.jonasthuresson.onealarmclock

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.jonasthuresson.onealarmclock.android.OneAlarmApplication
import com.jonasthuresson.onealarmclock.android.helpers.SystemAlarmManager
import com.jonasthuresson.onealarmclock.android.ui.alarms.AlarmsFragmentDirections
import com.jonasthuresson.onealarmclock.android.ui.triggeredalarm.TriggeredAlarmViewModel
import com.jonasthuresson.onealarmclock.db.AppDatabase
import kotlinx.android.synthetic.main.main_activity.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    val triggeredAlarmViewModel by viewModels<TriggeredAlarmViewModel>()

    @Inject
    lateinit var mAppDatabase: AppDatabase

    companion object{
        const val ACTION_ALARM = "com.jonasthuresson.onealarmclock.actions.alarm"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as OneAlarmApplication).getComponent()?.inject(this)
        setContentView(R.layout.main_activity)
        handleIntent(intent)
        // ATTENTION: This was auto-generated to handle app links.
        // ATTENTION: This was auto-generated to handle app links.
        val appLinkIntent = intent
        val appLinkAction = appLinkIntent.action
        val appLinkData = appLinkIntent.data
    }

    private fun handleIntent(intent: Intent?) {
        if(intent?.action == MainActivity.ACTION_ALARM){
            val id = intent?.getLongExtra(SystemAlarmManager.EXTRA_ALARM_ID, -1)
            nav_host_fragment.findNavController().navigate(AlarmsFragmentDirections.actionMainFragmentToTriggeredAlarm(id))
        }
    }
}
