package com.example.mhcampaign.data.hunters

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface HunterDao {

    @Query("SELECT * from hunters_table")
    fun getHunters(): Flow<List<HunterEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHunter(item: HunterEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHunterList(hunterList: List<HunterEntity>)

    @Update
    suspend fun updateHunter (hunter: HunterEntity)
}