package com.example.mhcampaign.domain.campaign

import com.example.mhcampaign.data.campaign.CampaignRepository
import com.example.mhcampaign.model.CampaignModel
import kotlinx.coroutines.flow.Flow

class GetCampaignsUseCase  constructor(
    private val repository: CampaignRepository
) {
    operator fun invoke(): Flow<List<CampaignModel>> {

        return repository.campaigns

    }
}