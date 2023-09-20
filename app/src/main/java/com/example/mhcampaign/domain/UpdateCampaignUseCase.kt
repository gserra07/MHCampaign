package com.example.mhcampaign.domain

import com.example.mhcampaign.data.CampaignRepository
import com.example.mhcampaign.model.CampaignModel

class UpdateCampaignUseCase constructor(private val  campaignRepository: CampaignRepository) {

    suspend operator fun invoke(campaignModel: CampaignModel){
        campaignRepository.updateCampaign(campaignModel)
    }
}