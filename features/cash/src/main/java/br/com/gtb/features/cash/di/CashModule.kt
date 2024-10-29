package br.com.gtb.features.cash.di

import br.com.gtb.features.cash.data.irepostory.ICashRepository
import br.com.gtb.features.cash.data.repository.CashRepository
import br.com.gtb.features.cash.presenter.viewmodel.CashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cashModule = module {
    factory<ICashRepository> {
        CashRepository(
            dao = get(),
            service = get()
        )
    }

    viewModel { CashViewModel(repository = get()) }
}