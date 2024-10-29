package br.com.gtb.features.product.data.irepostory

import br.com.gtb.libraries.data.model.Product
import br.com.gtb.libraries.uicore.utils.Result
import androidx.lifecycle.LiveData

interface IProductRepository {

    enum class ProductRepositoryStatus {
        GetListFromServer,
    }

    val statusGetListFromServer: LiveData<Result<List<Product>>>

    suspend fun getList()
    suspend fun getListFromServer()
    suspend fun createListToDb(list: List<Product>)
    fun getListFromDb(): LiveData<List<Product>?>
    fun resetStatus(status: ProductRepositoryStatus)
}