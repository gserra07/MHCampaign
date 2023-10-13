package com.example.mhcampaign.ui.huntersView

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mhcampaign.data.di.DatabaseModule
import com.example.mhcampaign.data.hunters.HuntersRepository
import com.example.mhcampaign.domain.hunters.AddHunterUseCase
import com.example.mhcampaign.domain.hunters.GetHuntersUseCase
import com.example.mhcampaign.domain.hunters.UpdateHunterUseCase
import com.example.mhcampaign.model.HunterDataModel
import com.example.mhcampaign.ui.HuntersUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HuntersViewModel(
    context: Context
) : ViewModel() {

    var addHunterUseCase: AddHunterUseCase
    var getHuntersUseCase: GetHuntersUseCase
    var updateHunterUseCase: UpdateHunterUseCase

    var uiStateHunters: StateFlow<HuntersUIState> = MutableStateFlow(HuntersUIState.Loading)

    private val _selectedHunter = MutableLiveData<HunterDataModel?>()
    val selectedHunter: LiveData<HunterDataModel?> = _selectedHunter

    private val _hunterDialogVisibility = MutableLiveData(false)
    val hunterDialogVisibility: LiveData<Boolean> = _hunterDialogVisibility

    init {
        val huntersDao = DatabaseModule().provideDatabase(context).huntersDao()
        val huntersRepository = HuntersRepository(huntersDao)

        addHunterUseCase = AddHunterUseCase(huntersRepository)
        getHuntersUseCase = GetHuntersUseCase(huntersRepository)
        updateHunterUseCase = UpdateHunterUseCase(huntersRepository)

        uiStateHunters = getHuntersUseCase().map(HuntersUIState::SuccessHunters).catch { Error(it) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HuntersUIState.Loading)
    }

    fun onSelectedHunterChange(hunterData: HunterDataModel?) {
        _selectedHunter.value = hunterData
    }

    fun onHunterDialogVisibilityChange(visibility: Boolean) {
        _hunterDialogVisibility.value = visibility
    }

    fun onAddHunter(item: HunterDataModel) {
        viewModelScope.launch {
            addHunterUseCase(item)
        }
    }

    fun onUpdateHunter(item: HunterDataModel) {
        viewModelScope.launch {
            updateHunterUseCase(item)
        }
    }

}