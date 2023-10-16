package com.example.mhcampaign.domain.hunters

import com.example.mhcampaign.data.hunters.HuntersRepository
import com.example.mhcampaign.model.HunterDataModel
import kotlinx.coroutines.flow.Flow

class GetHuntersUseCase  constructor(
    private val repository: HuntersRepository
) {
    operator fun invoke(): Flow<List<HunterDataModel>> {

        return repository.hunters

    }
}