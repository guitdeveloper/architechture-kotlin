package br.com.gtb.features.product.di

import br.com.gtb.features.product.data.irepostory.IProductRepository
import br.com.gtb.features.product.data.repository.ProductRepository
import br.com.gtb.features.product.presenter.viewmodel.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productModule = module {

    //Repository
    factory<IProductRepository> {
        ProductRepository(
            dao = get(),
            service = get()
        )
    }

    //ViewModel
    viewModel { ProductViewModel(repository = get()) }

}