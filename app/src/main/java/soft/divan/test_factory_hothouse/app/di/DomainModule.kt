package soft.divan.test_factory_hothouse.app.di

import org.koin.dsl.module
import soft.divan.test_factory_hothouse.data.repositoryImp.AuthRepositoryImpl
import soft.divan.test_factory_hothouse.data.repositoryImp.MainRepositoryImpl
import soft.divan.test_factory_hothouse.domain.api.ApiFactory
import soft.divan.test_factory_hothouse.domain.repository.AuthRepository
import soft.divan.test_factory_hothouse.domain.repository.MainRepository
import soft.divan.test_factory_hothouse.domain.rest.OkHttpClientFactory
import soft.divan.test_factory_hothouse.domain.rest.RetrofitFactory
import soft.divan.test_factory_hothouse.domain.rest.interseptors.AuthAuthenticator
import soft.divan.test_factory_hothouse.domain.rest.interseptors.AuthInterceptor
import soft.divan.test_factory_hothouse.domain.rest.interseptors.LoggingInterceptor
import soft.divan.test_factory_hothouse.domain.usecases.CheckAuthCodeUseCase
import soft.divan.test_factory_hothouse.domain.usecases.GetCurrentUserUseCase
import soft.divan.test_factory_hothouse.domain.usecases.SendAuthCodeUseCase

val domainModule = module {


    factory { OkHttpClientFactory(get(), get(), get()) }

    factory { RetrofitFactory(get()) }

    single { LoggingInterceptor() }

    single { AuthInterceptor(get()) }

    single { AuthAuthenticator(get()) }


    single<MainRepository> { MainRepositoryImpl(get()) }

    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    single { ApiFactory(get()).serverApi }

    single { ApiFactory(get()).authServiceApi }

    single { AuthInterceptor(get()) }


    factory { SendAuthCodeUseCase(get()) }
    factory { CheckAuthCodeUseCase(get()) }
    factory { GetCurrentUserUseCase(get()) }


}