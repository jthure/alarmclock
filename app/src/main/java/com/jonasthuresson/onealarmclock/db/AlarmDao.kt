package com.jonasthuresson.onealarmclock.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlarmDao {
    @Query("SELECT * FROM alarm")
    suspend fun getAll(): List<Alarm>

    @Query("SELECT * FROM alarm WHERE id IN (:alarmIds)")
    suspend fun loadAllByIds(alarmIds: LongArray): List<Alarm>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg alarms: Alarm)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(alarm: Alarm): Long

    @Delete
    suspend fun delete(user: Alarm)
}