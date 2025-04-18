package soft.divan.test_factory_hothouse.app.di

import org.koin.dsl.module
import soft.divan.test_factory_hothouse.data.local.source.impl.TokenManager

val dataModule = module {
    single { TokenManager(get()) }

}