package com.example.mhcampaign.domain.hunters

import com.example.mhcampaign.data.campaign.CampaignRepository
import com.example.mhcampaign.data.hunters.HuntersRepository
import com.example.mhcampaign.model.CampaignModel
import com.example.mhcampaign.model.HunterDataModel

class AddHunterUseCase  constructor(private val  huntersRepository: HuntersRepository) {

    suspend operator fun invoke(hunterDataModel: HunterDataModel){
        huntersRepository.addHunter(hunterDataModel)
    }
}