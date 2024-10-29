package br.com.gtb.features.spotlight.di

import br.com.gtb.features.spotlight.data.irepostory.ISpotlightRepository
import br.com.gtb.features.spotlight.data.repository.SpotlightRepository
import br.com.gtb.features.spotlight.presenter.viewmodel.SpotlightViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val spotlightModule = module {

    //Repository
    factory<ISpotlightRepository> {
        SpotlightRepository(
            dao = get(),
            service = get()
        )
    }

    //ViewModel
    viewModel { SpotlightViewModel(repository = get()) }

}