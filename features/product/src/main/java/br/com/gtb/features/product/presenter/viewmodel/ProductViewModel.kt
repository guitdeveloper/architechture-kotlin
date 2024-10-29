package br.com.gtb.features.product.presenter.viewmodel

import br.com.gtb.features.product.data.irepostory.IProductRepository
import br.com.gtb.libraries.data.model.Product
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

internal class ProductViewModel(
    private val repository: IProductRepository
) : ViewModel() {

    val statusGetListFromServer = repository.statusGetListFromServer

    fun getList() {
        viewModelScope.launch {
            repository.getList()
        }
    }

    fun createListToDb(list: List<Product>) {
        viewModelScope.launch {
            if (list.isNotEmpty())
                repository.createListToDb(list)
        }
    }

    fun getListFromDb(): LiveData<List<Product>?> {
        return repository.getListFromDb()
    }

    fun resetStatus(status: IProductRepository.ProductRepositoryStatus) =
        repository.resetStatus(status)

}