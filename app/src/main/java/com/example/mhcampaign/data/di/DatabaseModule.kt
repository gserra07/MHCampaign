package com.example.mhcampaign.data.di

import android.content.Context
import androidx.room.Room
import com.example.mhcampaign.data.CampaignDao
import com.example.mhcampaign.data.MHCampaignDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    private val MHCAMPAIGN_DATABASE_NAME = "MHCampaignDataBase"


    @Provides
    @Singleton
    fun provideCampaignDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MHCampaignDataBase::class.java,
            MHCAMPAIGN_DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideCampaignDao(campaignDatabase: MHCampaignDataBase): CampaignDao {
        return campaignDatabase.campaignDao()
    }

}