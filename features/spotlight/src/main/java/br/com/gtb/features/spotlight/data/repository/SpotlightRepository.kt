package br.com.gtb.features.spotlight.data.repository

import br.com.gtb.features.spotlight.data.irepostory.ISpotlightRepository
import br.com.gtb.libraries.data.dao.SpotlightDao
import br.com.gtb.libraries.data.entity.asDomainModel
import br.com.gtb.libraries.data.model.Spotlight
import br.com.gtb.libraries.data.model.asEntity
import br.com.gtb.libraries.data.response.ArchitectureProductResponse
import br.com.gtb.libraries.data.service.ArchitectureService
import br.com.gtb.libraries.uicore.exceptions.ListIsEmptyException
import br.com.gtb.libraries.uicore.extensions.resetStatus
import br.com.gtb.libraries.uicore.utils.Result
import br.com.gtb.libraries.uicore.utils.responseError
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import br.com.gtb.libraries.uicore.exceptions.ResponseNoBodyException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SpotlightRepository(
    val dao: SpotlightDao,
    val service: ArchitectureService,
) : ISpotlightRepository {

    private val _statusGetListFromServer =
        MutableLiveData<Result<List<Spotlight>>>()
    override val statusGetListFromServer: LiveData<Result<List<Spotlight>>>
        get() = _statusGetListFromServer

    override suspend fun getList() {
        withContext(Dispatchers.IO) {
            val list = getListFromDb()
            if (list.value.isNullOrEmpty())
                getListFromServer()
        }
    }

    override suspend fun getListFromServer() {
        withContext(Dispatchers.IO) {
            _statusGetListFromServer.postValue(Result.InProgress)
            service.getProducts().enqueue(object :
                Callback<ArchitectureProductResponse> {
                override fun onResponse(
                    call: Call<ArchitectureProductResponse>,
                    response: Response<ArchitectureProductResponse>
                ) {
                    try {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                if (it.spotlights.isEmpty()) {
                                    throw ListIsEmptyException()
                                } else {
                                    _statusGetListFromServer.postValue(
                                        Result.Success(it.spotlights)
                                    )
                                }
                            } ?: throw ResponseNoBodyException()
                        } else {
                            _statusGetListFromServer.postValue(
                                responseError(response.code())
                            )
                        }
                    } catch (ex: Exception) {
                        _statusGetListFromServer.postValue(
                            Result.Error(ex, null)
                        )
                    }
                }
                override fun onFailure(call: Call<ArchitectureProductResponse>, t: Throwable) {
                    _statusGetListFromServer.postValue(
                        Result.Error(t, null)
                    )
                }
            })
        }
    }

    override suspend fun createListToDb(list: List<Spotlight>) {
        withContext(Dispatchers.IO) {
            try {
                dao.clearAndInsert(list.asEntity())
            } catch (ex: Exception) {
                ex.toString()
            }
        }
    }

    override fun getListFromDb(): LiveData<List<Spotlight>?> {
        return dao.getList().map {
            it?.asDomainModel()
        }
    }

    override fun resetStatus(status: ISpotlightRepository.SpotlightRepositoryStatus) =
        when (status) {
            ISpotlightRepository.SpotlightRepositoryStatus.GetListFromServer -> _statusGetListFromServer.resetStatus()
        }

}