package br.com.gtb.features.spotlight.presenter.viewmodel

import br.com.gtb.features.spotlight.data.irepostory.ISpotlightRepository
import br.com.gtb.libraries.data.model.Spotlight
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

internal class SpotlightViewModel(
    private val repository: ISpotlightRepository
) : ViewModel() {

    val statusGetListFromServer = repository.statusGetListFromServer

    fun getList() {
        viewModelScope.launch {
            repository.getList()
        }
    }

    fun createListToDb(list: List<Spotlight>) {
        viewModelScope.launch {
            if (list.isNotEmpty())
                repository.createListToDb(list)
        }
    }

    fun getListFromDb(): LiveData<List<Spotlight>?> {
        return repository.getListFromDb()
    }

    fun resetStatus(status: ISpotlightRepository.SpotlightRepositoryStatus) =
        repository.resetStatus(status)

}