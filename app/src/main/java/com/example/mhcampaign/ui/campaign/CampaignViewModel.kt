package com.example.mhcampaign.ui.campaign

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mhcampaign.domain.AddCampaignUseCase
import com.example.mhcampaign.domain.GetCampaignsUseCase
import com.example.mhcampaign.model.CampaignModel
import com.example.mhcampaign.model.HunterData
import com.example.mhcampaign.ui.CampaignUIState
import com.example.mhcampaign.ui.CampaignUIState.Success
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

//@HiltViewModel
class CampaignViewModel constructor(
    private val addCampaignUseCase: AddCampaignUseCase,
    private val getCampaignsUseCase: GetCampaignsUseCase
) : ViewModel() {

    val uiState: StateFlow<CampaignUIState> =
        getCampaignsUseCase().map(::Success).catch { Error(it) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CampaignUIState.Loading)


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

//    init {
//        viewModelScope.launch {
//            uiState = getCampaignsUseCase().map(::Success).catch { Error(it) }
//                .stateIn(
//                    viewModelScope,
//                    SharingStarted.WhileSubscribed(5000),
//                    CampaignUIState.Loading
//                )
////            _campaignList.postValue(result)
//        }
//    }

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

