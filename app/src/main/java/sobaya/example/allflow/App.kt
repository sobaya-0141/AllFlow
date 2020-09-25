package sobaya.example.allflow

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import sobaya.app.util.AppConfig
import sobaya.example.allflow.di.networkModule
import sobaya.example.allflow.ui.main.MainViewModel

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, module))
        }
    }

    private val module = org.koin.dsl.module {
        factory { AppConfig(BuildConfig.test) }
        viewModel { MainViewModel(get()) }
    }


}
