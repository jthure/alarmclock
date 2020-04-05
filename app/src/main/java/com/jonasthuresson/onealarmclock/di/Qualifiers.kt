package com.itshaven.testapp.test_room.di

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
annotation class ApplicationContext

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
annotation class ActivityContext

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
annotation class DatabaseInfo