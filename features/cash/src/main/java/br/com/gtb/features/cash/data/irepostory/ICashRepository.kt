package br.com.gtb.features.cash.data.irepostory

import br.com.gtb.libraries.data.model.Cash
import br.com.gtb.libraries.uicore.utils.Result
import androidx.lifecycle.LiveData

internal interface ICashRepository {

    enum class CashRepositoryStatus {
        GetCashFromServer,
    }

    val statusGetCashFromServer: LiveData<Result<Cash>>

    suspend fun getCash()
    suspend fun getCashFromServer()
    suspend fun createCashToDb(cash: Cash)
    fun getCashFromDb(): LiveData<Cash?>
    fun resetStatus(status: CashRepositoryStatus)
}