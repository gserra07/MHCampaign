package com.example.mhcampaign.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CampaignDao {
    @Query("SELECT * from campaign_table")
    suspend fun getAllCampaigns(): List<CampaignEntity>

    @Query("SELECT * from campaign_table")
    fun getCampaigns(): Flow<List<CampaignEntity>>

    @Insert
    suspend fun addCampaign(item: CampaignEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCampaignList(list: List<CampaignEntity>)

}