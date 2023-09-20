package com.example.mhcampaign.domain

import com.example.mhcampaign.data.CampaignRepository
import com.example.mhcampaign.model.CampaignModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCampaignsUseCase  constructor(
    private val repository: CampaignRepository
) {
    operator fun invoke(): Flow<List<CampaignModel>> {

        return repository.campaigns

    }
}