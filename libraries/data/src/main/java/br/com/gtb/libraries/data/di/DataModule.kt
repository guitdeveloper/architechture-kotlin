package br.com.gtb.libraries.data.di

import br.com.gtb.libraries.data.ArchitectureDatabase
import br.com.gtb.libraries.data.getDatabase
import br.com.gtb.libraries.data.getRetrofit
import br.com.gtb.libraries.data.service.ArchitectureService
import org.koin.dsl.module
import retrofit2.Retrofit

val dataModule = module {
    //Database
    factory { getDatabase(context = get()) }

    //Dao
    factory { get<ArchitectureDatabase>().cashDao }
    factory { get<ArchitectureDatabase>().spotlightDao }
    factory { get<ArchitectureDatabase>().productDao }

    //Retrofit
    factory { getRetrofit() }

    //Service
    factory { get<Retrofit>().create(ArchitectureService::class.java) }
}