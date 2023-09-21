package com.example.mhcampaign.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mhcampaign.data.campaign.CampaignDao
import com.example.mhcampaign.data.campaign.CampaignEntity
import com.example.mhcampaign.data.hunters.HunterDao
import com.example.mhcampaign.data.hunters.HunterEntity
import com.example.xGuardiapp.database.MyTypeConverters

@TypeConverters(value = [MyTypeConverters::class])
@Database(entities = [CampaignEntity::class,HunterEntity::class], version = 2)
abstract class MHCampaignDataBase : RoomDatabase() {
    abstract fun campaignDao(): CampaignDao
    abstract fun huntersDao():HunterDao
}