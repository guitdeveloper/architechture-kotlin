package br.com.gtb.architecture

import android.app.Application
import br.com.gtb.features.cash.di.cashModule
import br.com.gtb.features.product.di.productModule
import br.com.gtb.features.spotlight.di.spotlightModule
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ProcessLifecycleOwner
import br.com.gtb.libraries.data.clearDatabase
import br.com.gtb.libraries.data.di.dataModule
import br.com.gtb.libraries.data.getDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ArchitectureApplication : Application(), LifecycleObserver {

    override fun onCreate() {
        super.onCreate()

        val list = mutableListOf(
            dataModule,
            cashModule,
            spotlightModule,
            productModule,
        )
        startKoin {
            androidContext(this@ArchitectureApplication)
            modules(list)
        }

        getDatabase(this@ArchitectureApplication).clearDatabase()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

}