package com.example.mhcampaign.domain.campaign

import com.example.mhcampaign.data.campaign.CampaignRepository
import com.example.mhcampaign.model.CampaignModel

class UpdateCampaignUseCase constructor(private val  campaignRepository: CampaignRepository) {

    suspend operator fun invoke(campaignModel: CampaignModel){
        campaignRepository.updateCampaign(campaignModel)
    }
}