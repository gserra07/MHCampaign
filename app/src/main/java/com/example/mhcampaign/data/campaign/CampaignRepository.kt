package com.example.mhcampaign.data.campaign

import com.example.mhcampaign.model.CampaignModel
import com.example.mhcampaign.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CampaignRepository @Inject constructor(
    private var campaignDao: CampaignDao
) {

    val campaigns: Flow<List<CampaignModel>> =
        campaignDao.getCampaigns().map { items -> items.map { it.toDomain() } }

    suspend fun addCampaign(campaignModel: CampaignModel) {
        campaignDao.addCampaign(campaignModel.toDataBase(true))
    }

    suspend fun updateCampaign(campaignModel: CampaignModel) {
        campaignDao.updateCampaign(campaignModel.toDataBase())
    }
}