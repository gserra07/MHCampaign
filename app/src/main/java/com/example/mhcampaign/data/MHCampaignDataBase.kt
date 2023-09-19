package com.example.mhcampaign.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CampaignEntity::class], version = 1)
abstract class MHCampaignDataBase : RoomDatabase() {
    abstract fun campaignDao(): CampaignDao
}