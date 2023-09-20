package com.example.mhcampaign.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.xGuardiapp.database.MyTypeConverters

@TypeConverters(value = [MyTypeConverters::class])
@Database(entities = [CampaignEntity::class], version = 1)
abstract class MHCampaignDataBase : RoomDatabase() {
    abstract fun campaignDao(): CampaignDao
}