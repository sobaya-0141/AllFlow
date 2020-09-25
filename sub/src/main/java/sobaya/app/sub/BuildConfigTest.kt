package sobaya.app.sub

import org.koin.core.KoinComponent
import org.koin.core.inject
import sobaya.app.util.AppConfig

class BuildConfigTest: KoinComponent {
    private val appConfig: AppConfig by inject()

    fun getConfig(): String = appConfig.test
}
