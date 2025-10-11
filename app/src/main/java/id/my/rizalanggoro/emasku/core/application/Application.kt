package id.my.rizalanggoro.emasku.core.application

import android.app.Application
import id.my.rizalanggoro.emasku.core.di.managerModule
import id.my.rizalanggoro.emasku.core.di.serviceModule
import id.my.rizalanggoro.emasku.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(
                managerModule,
                serviceModule,
                viewModelModule,
            )
        }
    }
}