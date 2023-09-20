package com.example.mhcampaign.ui

import com.example.mhcampaign.model.CampaignModel

sealed interface CampaignUIState {
    object Loading : CampaignUIState
    data class Error(val throwable: Throwable) : CampaignUIState
    data class Success(val campaigns: List<CampaignModel>) : CampaignUIState
}