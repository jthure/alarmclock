//package com.jonasthuresson.onealarmclock.di

//import com.jonasthuresson.onealarmclock.android.MainActivity
//import dagger.Binds
//import dagger.Module
//import dagger.android.AndroidInjector
//import dagger.android.ContributesAndroidInjector
//import dagger.multibindings.ClassKey
//import dagger.multibindings.IntoMap


//@Module(subcomponents = [])
//public abstract class ActivityBuilder {
//    @ContributesAndroidInjector(modules = [SpotifyManagerModule::class])
//    abstract fun bindMainActivity(): MainActivity?
//
//    @Binds
//    @IntoMap
//    @ClassKey(MainActivity::class)
//    abstract fun bindAndroidInjectorFactory(
//        builder: MainActivityComponent.Factory?
//    ): MainActivityComponent
//
//}