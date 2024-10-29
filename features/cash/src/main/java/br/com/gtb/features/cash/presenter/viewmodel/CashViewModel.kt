package br.com.gtb.features.cash.presenter.viewmodel

import br.com.gtb.features.cash.data.irepostory.ICashRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gtb.libraries.data.model.Cash
import kotlinx.coroutines.launch

internal class CashViewModel(
    private val repository: ICashRepository
) : ViewModel() {

    val statusGetListFromServer = repository.statusGetCashFromServer

    fun getCash() {
        viewModelScope.launch {
            repository.getCash()
        }
    }

    fun createCashToDb(cash: Cash) {
        viewModelScope.launch {
            repository.createCashToDb(cash)
        }
    }

    fun getCashFromDb(): LiveData<Cash?> {
        return repository.getCashFromDb()
    }

    fun resetStatus(status: ICashRepository.CashRepositoryStatus) =
        repository.resetStatus(status)
}