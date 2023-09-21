package com.example.mhcampaign.data.hunters

import com.example.mhcampaign.model.HunterDataModel
import com.example.mhcampaign.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HuntersRepository @Inject constructor(
    private var hunterDao: HunterDao
) {
    val hunters: Flow<List<HunterDataModel>> =
        hunterDao.getHunters().map { items -> items.map { it.toDomain() } }

    suspend fun addHunter(hunterDataModel: HunterDataModel) {
        hunterDao.addHunter(hunterDataModel.toDataBase())
    }

    suspend fun updateHunter(hunterDataModel: HunterDataModel) {
        hunterDao.updateHunter(hunterDataModel.toDataBase())
    }
}