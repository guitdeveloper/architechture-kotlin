package br.com.gtb.features.cash.data.repository

import br.com.gtb.features.cash.data.irepostory.ICashRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import br.com.gtb.libraries.data.dao.CashDao
import br.com.gtb.libraries.data.entity.asDomainModel
import br.com.gtb.libraries.data.model.Cash
import br.com.gtb.libraries.data.model.asEntity
import br.com.gtb.libraries.data.response.ArchitectureProductResponse
import br.com.gtb.libraries.data.service.ArchitectureService
import br.com.gtb.libraries.uicore.exceptions.ResponseNoBodyException
import br.com.gtb.libraries.uicore.extensions.resetStatus
import br.com.gtb.libraries.uicore.utils.Result
import br.com.gtb.libraries.uicore.utils.responseError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class CashRepository(
    val dao: CashDao,
    val service: ArchitectureService,
) : ICashRepository {

    private val _statusGetCashFromServer =
        MutableLiveData<Result<Cash>>()
    override val statusGetCashFromServer: LiveData<Result<Cash>>
        get() = _statusGetCashFromServer

    override suspend fun getCash() {
        withContext(Dispatchers.IO) {
            val cash = getCashFromDb()
            if (cash.value == null)
                getCashFromServer()
        }
    }

    override suspend fun getCashFromServer() {
        withContext(Dispatchers.IO) {
            _statusGetCashFromServer.postValue(Result.InProgress)
            service.getProducts().enqueue(object :
                Callback<ArchitectureProductResponse> {
                override fun onResponse(
                    call: Call<ArchitectureProductResponse>,
                    response: Response<ArchitectureProductResponse>
                ) {
                    try {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                _statusGetCashFromServer.postValue(
                                    Result.Success(it.cash)
                                )
                            } ?: throw ResponseNoBodyException()
                        } else {
                            _statusGetCashFromServer.postValue(
                                responseError(response.code())
                            )
                        }
                    } catch (ex: Exception) {
                        _statusGetCashFromServer.postValue(
                            Result.Error(ex, null)
                        )
                    }
                }

                override fun onFailure(call: Call<ArchitectureProductResponse>, t: Throwable) {
                    _statusGetCashFromServer.postValue(
                        Result.Error(t, null)
                    )
                }
            })
        }
    }

    override suspend fun createCashToDb(cash: Cash) {
        withContext(Dispatchers.IO) {
            try {
                dao.clearAndInsert(cash.asEntity())
            } catch (ex: Exception) {
                ex.toString()
            }
        }
    }

    override fun getCashFromDb(): LiveData<Cash?> {
        return dao.getList().map {
            it?.asDomainModel()
        }
    }

    override fun resetStatus(status: ICashRepository.CashRepositoryStatus) =
        when (status) {
            ICashRepository.CashRepositoryStatus.GetCashFromServer -> _statusGetCashFromServer.resetStatus()
        }

}