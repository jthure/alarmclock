//package com.jonasthuresson.onealarmclock.di
//
//import com.jonasthuresson.onealarmclock.android.MainActivity
//import dagger.BindsInstance
//import dagger.Subcomponent
//import dagger.android.AndroidInjector
//
//
//@Subcomponent(modules = [MainActivityModule::class, FragmentBuilder::class])
//interface MainActivityComponent : AndroidInjector<MainActivity?> {
//    @Subcomponent.Factory
//    interface Factory{
//        fun create(): MainActivityComponent
//    }
//}