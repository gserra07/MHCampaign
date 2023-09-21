package com.example.mhcampaign.data.di

import android.content.Context
import androidx.room.Room
import com.example.mhcampaign.data.campaign.CampaignDao
import com.example.mhcampaign.data.MHCampaignDataBase
import com.example.mhcampaign.data.hunters.HunterDao
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
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MHCampaignDataBase::class.java,
            MHCAMPAIGN_DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun provideCampaignDao(campaignDatabase: MHCampaignDataBase): CampaignDao {
        return campaignDatabase.campaignDao()
    }
    @Singleton
    @Provides
    fun provideHuntersDao(campaignDatabase: MHCampaignDataBase): HunterDao {
        return campaignDatabase.huntersDao()
    }

}