package com.jonasthuresson.onealarmclock.di

import android.content.Context
import androidx.room.Room
import com.itshaven.testapp.test_room.di.ApplicationContext
import com.itshaven.testapp.test_room.di.DatabaseInfo
import com.jonasthuresson.onealarmclock.db.AlarmDao
import com.jonasthuresson.onealarmclock.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule(@ApplicationContext context: Context) {
    @ApplicationContext
    private val mContext: Context = context

    @DatabaseInfo
    private val mDBName = "test_database.db"

    @Singleton
    @Provides
    fun provideDatabase(): AppDatabase {
        return Room.databaseBuilder(
            mContext,
            AppDatabase::class.java,
            mDBName
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return mDBName
    }

    @Singleton
    @Provides
    fun provideAlarmDao(db: AppDatabase): AlarmDao {
        return db.alarmDao()
    }


}