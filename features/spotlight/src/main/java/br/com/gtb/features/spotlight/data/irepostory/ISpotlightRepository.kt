package br.com.gtb.features.spotlight.data.irepostory

import androidx.lifecycle.LiveData
import br.com.gtb.libraries.data.model.Spotlight
import br.com.gtb.libraries.uicore.utils.Result

interface ISpotlightRepository {

    enum class SpotlightRepositoryStatus {
        GetListFromServer,
    }

    val statusGetListFromServer: LiveData<Result<List<Spotlight>>>

    suspend fun getList()
    suspend fun getListFromServer()
    suspend fun createListToDb(list: List<Spotlight>)
    fun getListFromDb(): LiveData<List<Spotlight>?>
    fun resetStatus(status: SpotlightRepositoryStatus)
}