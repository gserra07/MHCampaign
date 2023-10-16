package com.example.mhcampaign.domain.hunters

import com.example.mhcampaign.data.hunters.HuntersRepository
import com.example.mhcampaign.model.HunterDataModel

class UpdateHunterUseCase constructor(private val  huntersRepository: HuntersRepository) {

    suspend operator fun invoke(hunterDataModel: HunterDataModel){
        huntersRepository.updateHunter(hunterDataModel)
    }
}