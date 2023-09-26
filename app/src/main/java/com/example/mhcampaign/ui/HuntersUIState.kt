package com.example.mhcampaign.ui

import com.example.mhcampaign.model.HunterDataModel

sealed interface HuntersUIState {
    object Loading : HuntersUIState
    data class Error(val throwable: Throwable) : HuntersUIState
    data class SuccessHunters(val hunters: List<HunterDataModel>) : HuntersUIState
}