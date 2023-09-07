package com.example.mhcampaign.huntersView

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mhcampaign.model.HunterData

class HuntersViewModel(
    hunterListIn: MutableList<HunterData> = mutableListOf()
) {
    private val _hunterList = MutableLiveData(hunterListIn)
    val hunterList: LiveData<MutableList<HunterData>> = _hunterList

    private val _selectedHunter = MutableLiveData<HunterData?>()
    val selectedHunter: LiveData<HunterData?> = _selectedHunter

    private val _hunterDialogVisibility = MutableLiveData(false)
    val hunterDialogVisibility: LiveData<Boolean> = _hunterDialogVisibility

    private val _inventoryDialogVisibility = MutableLiveData(false)
    val inventoryDialogVisibility: LiveData<Boolean> = _inventoryDialogVisibility


    fun onSelectedHunterChange(hunterData: HunterData?) {
        _selectedHunter.value = hunterData
    }

    fun onHunterDialogVisibilityChange(visibility: Boolean) {
        _hunterDialogVisibility.value = visibility
    }

    fun onInventoryDialogVisibilityChange(visibility: Boolean) {
        _inventoryDialogVisibility.value = visibility
    }

    fun addHunter(item: HunterData) {
        _hunterList.value?.add(item)
    }

}