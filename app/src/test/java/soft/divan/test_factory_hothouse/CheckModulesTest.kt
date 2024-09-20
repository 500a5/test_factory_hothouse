package soft.divan.test_factory_hothouse


import org.junit.Test

import org.koin.dsl.koinApplication
import org.koin.test.KoinTest

import org.koin.test.check.checkModules
import soft.divan.test_factory_hothouse.app.di.dataModule
import soft.divan.test_factory_hothouse.app.di.domainModule
import soft.divan.test_factory_hothouse.app.di.presentationModule

class CheckModulesTest : KoinTest {

    @Test
    fun verifyKoinApp() {
        koinApplication {
            modules(presentationModule, dataModule, domainModule)
            checkModules()
        }
    }
}