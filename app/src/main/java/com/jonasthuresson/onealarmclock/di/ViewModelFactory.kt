package com.jonasthuresson.onealarmclock.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jonasthuresson.onealarmclock.android.ui.addalarm.AddAlarmViewModel
import com.jonasthuresson.onealarmclock.android.ui.alarms.AlarmsViewModel
import com.jonasthuresson.onealarmclock.android.ui.triggeredalarm.TriggeredAlarmViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AlarmsViewModel::class)
    internal abstract fun alarmsViewModel(viewModel: AlarmsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TriggeredAlarmViewModel::class)
    internal abstract fun triggeredAlarmViewModel(viewModel: TriggeredAlarmViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddAlarmViewModel::class)
    internal abstract fun addAlarmViewModel(viewModel: AddAlarmViewModel): ViewModel

    //Add more ViewModels here
}