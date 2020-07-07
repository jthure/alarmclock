package com.jonasthuresson.onealarmclock.di

import com.jonasthuresson.onealarmclock.android.services.SpotifyAlarmService
import com.jonasthuresson.onealarmclock.android.ui.addalarm.AddAlarmFragment
import com.jonasthuresson.onealarmclock.android.ui.addalarm.soundsources.SpotifySoundSourceFragment
import com.jonasthuresson.onealarmclock.android.ui.alarms.AlarmsFragment
import com.jonasthuresson.onealarmclock.android.ui.triggeredalarm.TriggeredAlarmFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
public abstract class FragmentBuilder {
    @ContributesAndroidInjector()
    abstract fun bindAlarmsFragment(): AlarmsFragment?

    @ContributesAndroidInjector()
    abstract fun bindAddAlarmFragment(): AddAlarmFragment?

    @ContributesAndroidInjector()
    abstract fun bindTriggeredAlarmFragment(): TriggeredAlarmFragment?

    @ContributesAndroidInjector()
    abstract fun bindSpotifySoundSourceFragment(): SpotifySoundSourceFragment?

    @ContributesAndroidInjector()
    abstract fun bindSpotifyAlarmService(): SpotifyAlarmService?

}