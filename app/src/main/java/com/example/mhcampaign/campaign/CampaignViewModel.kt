package com.example.mhcampaign.campaign

import androidx.constraintlayout.compose.Visibility
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mhcampaign.model.CampaignModel
import com.example.mhcampaign.model.HunterData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CampaignViewModel @Inject constructor(

) : ViewModel() {
    private val _campaignList = MutableLiveData<MutableList<CampaignModel>>()
    val campaignList: LiveData<MutableList<CampaignModel>> = _campaignList

    private val _hunterList = MutableLiveData<MutableList<HunterData>>()
    val hunterList: LiveData<MutableList<HunterData>> = _hunterList

    private val _selectedCampaign = MutableLiveData<CampaignModel>()
    val selectedCampaign: LiveData<CampaignModel> = _selectedCampaign

    private val _selectedCampaignIndex = MutableLiveData<Int>()
    val selectedCampaignIndex: LiveData<Int> = _selectedCampaignIndex

    private val _campaignHunters = MutableLiveData<MutableList<HunterData?>>()
    val campaignHunters: LiveData<MutableList<HunterData?>> = _campaignHunters

    private val _selectedHunter = MutableLiveData<HunterData?>()
    val selectedHunter: LiveData<HunterData?> = _selectedHunter

    private val _hunterDialogVisibility = MutableLiveData<Boolean>(false)
    val hunterDialogVisibility: LiveData<Boolean> = _hunterDialogVisibility

    private val _inventoryDialogVisibility = MutableLiveData<Boolean>(false)
    val inventoryDialogVisibility: LiveData<Boolean> = _inventoryDialogVisibility


    fun init(
        campaignListIn: MutableList<CampaignModel> = mutableListOf(),
        hunterListIn: MutableList<HunterData> = mutableListOf()
    ) {
        _campaignList.value = campaignListIn
        _hunterList.value = hunterListIn
        _selectedCampaignIndex.value = 0
        _selectedCampaign.value = _campaignList.value?.get(0)
        _campaignHunters.value = _hunterList.value?.filter { it.campaignId == campaignListIn[0].id }
            ?.toMutableList()
        makeCampaignHunters()
    }

    fun onCampaignIndexChange(index: Int) {
        _selectedCampaignIndex.value = index
        _selectedCampaign.value = _campaignList.value?.get(index)
        makeCampaignHunters()
    }

    fun onSelectedHunterChange(hunterData: HunterData?) {
        _selectedHunter.value = hunterData
    }

    fun onPotionsChange(potions: Int) {
        _selectedCampaign.value?.potions(potions)
    }

    fun onDaysChange(days: Int) {
        _selectedCampaign.value?.days(days)
    }

    fun onHunterDialogVisibilityChange(visibility: Boolean) {
        _hunterDialogVisibility.value = visibility
    }

    fun onInventoryDialogVisibilityChange(visibility: Boolean) {
        _inventoryDialogVisibility.value = visibility
    }

    fun removeCampaignHunter(hunter: HunterData?) {

        if (hunter != null && _hunterList.value?.any { it == hunter } == true) {
            _hunterList.value?.filter { it == hunter }?.get(0)?.campaignId(-1)
        }
        makeCampaignHunters()
    }

    fun addCampaignHunter(hunterPrevious: HunterData?, hunter: HunterData?) {
        if (hunter != null && hunter != hunterPrevious) {
            if (_hunterList.value?.any { it == hunter } == true) {
                _selectedCampaignIndex.value?.let { campaignIndex ->
                    _hunterList.value?.filter { it == hunter }?.get(0)?.campaignId(
                        campaignIndex
                    )
                }
            }
            removeCampaignHunter(hunterPrevious)
        }
    }

    fun makeCampaignHunters(
    ) {
        val list = mutableListOf<HunterData?>()
        _hunterList.value?.filter { it.campaignId == _selectedCampaign.value?.id }?.forEach {
            list.add(it)
        }

        while (list.size < 4) {
            list.add(null)
        }
        _campaignHunters.value = list
    }

}