package com.example.mhcampaign.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CampaignDao {
    @Query("SELECT * from campaign_table")
    suspend fun getAllCampaigns(): List<CampaignEntity>

    @Query("SELECT * from campaign_table")
    fun getCampaigns(): Flow<List<CampaignEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCampaign(item: CampaignEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCampaignList(list: List<CampaignEntity>)

    @Update
    suspend fun updateCampaign (item: CampaignEntity)

}