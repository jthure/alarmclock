package com.jonasthuresson.onealarmclock.di

import com.jonasthuresson.onealarmclock.android.services.SpotifyAlarmService
import com.jonasthuresson.onealarmclock.android.ui.addalarm.AddAlarmFragment
import com.jonasthuresson.onealarmclock.android.ui.addalarm.soundsources.SpotifySoundSourceFragment
import com.jonasthuresson.onealarmclock.android.ui.alarms.AlarmsFragment
import com.jonasthuresson.onealarmclock.android.ui.triggeredalarm.TriggeredAlarmFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBuilder {

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun bindAlarmsFragment(): AlarmsFragment?

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun bindAddAlarmFragment(): AddAlarmFragment?

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun bindTriggeredAlarmFragment(): TriggeredAlarmFragment?

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun bindSpotifySoundSourceFragment(): SpotifySoundSourceFragment?

    @FragmentScope
    @ContributesAndroidInjector()
    abstract fun bindSpotifyAlarmService(): SpotifyAlarmService?

}