package com.jonasthuresson.onealarmclock.di

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier
import javax.inject.Scope

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
annotation class ApplicationContext

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
annotation class ActivityContext

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
annotation class DatabaseInfo

@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class ActivityScope

@Scope
@Retention(RetentionPolicy.RUNTIME)
annotation class FragmentScope