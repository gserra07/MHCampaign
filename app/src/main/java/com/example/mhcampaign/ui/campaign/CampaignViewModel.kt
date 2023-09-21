package com.example.mhcampaign.ui.campaign

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mhcampaign.data.CampaignRepository
import com.example.mhcampaign.data.di.DatabaseModule
import com.example.mhcampaign.domain.AddCampaignUseCase
import com.example.mhcampaign.domain.GetCampaignsUseCase
import com.example.mhcampaign.domain.UpdateCampaignUseCase
import com.example.mhcampaign.model.CampaignModel
import com.example.mhcampaign.model.HunterData
import com.example.mhcampaign.model.MonsterDataModel
import com.example.mhcampaign.model.enums.Monster
import com.example.mhcampaign.ui.CampaignUIState
import com.example.mhcampaign.ui.CampaignUIState.Success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class CampaignViewModel @Inject constructor(
    context: Context
) : ViewModel() {
    var addCampaignUseCase: AddCampaignUseCase
    var getCampaignsUseCase: GetCampaignsUseCase
    var updateCampaignUseCase: UpdateCampaignUseCase

    var uiState: StateFlow<CampaignUIState> = MutableStateFlow(CampaignUIState.Loading)


//    private var uiState: StateFlow<CampaignUIState>  =  MutableStateFlow(CampaignUIState.Loading)

//    private val _campaignList = MutableLiveData<MutableList<CampaignModel>>(mutableListOf())
//    val campaignList: LiveData<MutableList<CampaignModel>> = _campaignList

    private val _hunterList = MutableLiveData<MutableList<HunterData>>(mutableListOf())
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

    init {
        val campaignDao =
            DatabaseModule().provideCampaignDatabase(context).campaignDao()
        val repository = CampaignRepository(campaignDao)
        getCampaignsUseCase = GetCampaignsUseCase(repository)
        addCampaignUseCase = AddCampaignUseCase(repository)
        updateCampaignUseCase = UpdateCampaignUseCase(repository)
        uiState = getCampaignsUseCase().map(::Success).catch { Error(it) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CampaignUIState.Loading)
    }

    fun init(
        campaignListIn: MutableList<CampaignModel> = mutableListOf(),
        hunterListIn: MutableList<HunterData> = mutableListOf(),
    ) {

//       var cosa = getCampaignsUseCase().map(::Success).catch { Error(it) }
//            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CampaignUIState.Loading)

//        _campaignList.value = campaignListIn
        _hunterList.value = hunterListIn
        _selectedCampaignIndex.value = 0

        _selectedCampaign.value = campaignListIn[0]
        _campaignHunters.value = _hunterList.value?.filter { it.campaignId == campaignListIn[0].id }
            ?.toMutableList()
        makeCampaignHunters()
    }

    fun onCampaignIndexChange(index: Int, campaignModel: CampaignModel) {
        _selectedCampaignIndex.value = index
        _selectedCampaign.value = campaignModel
        makeCampaignHunters()
    }

    fun onSelectedHunterChange(hunterData: HunterData?) {
        _selectedHunter.value = hunterData
    }

    fun onPotionsChange(potions: Int) {
        _selectedCampaign.value?.potions(potions)
        updateBBDD()
    }

    fun onDaysChange(days: Int) {
        _selectedCampaign.value?.days(days)
        updateBBDD()
    }

    fun onAddMonster(monster: Monster) {
        _selectedCampaign.value?.addMonster(monster)

        updateBBDD()
    }

    fun onChangeMonster(monsterList: MutableList<MonsterDataModel>) {
        _selectedCampaign.value?.updateMonsterList(monsterList)
        updateBBDD()
    }

    private fun updateBBDD() {
        viewModelScope.launch {
            _selectedCampaign.value?.let { updateCampaignUseCase(it) }
        }
    }

    fun onHunterDialogVisibilityChange(visibility: Boolean) {
        _hunterDialogVisibility.value = visibility
    }

    fun onInventoryDialogVisibilityChange(visibility: Boolean) {
        _inventoryDialogVisibility.value = visibility
    }

    fun onAddCampaign(campaignModel: CampaignModel) {
        viewModelScope.launch {
            addCampaignUseCase(campaignModel)
        }
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
                _selectedCampaign.value?.id?.let { campaignId ->
                    _hunterList.value?.filter { it == hunter }?.get(0)?.campaignId(
                        campaignId
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

