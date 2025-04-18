package soft.divan.test_factory_hothouse.app.di

import org.koin.dsl.module
import soft.divan.test_factory_hothouse.data.remote.repositoryImp.AuthRepositoryImpl
import soft.divan.test_factory_hothouse.data.remote.repositoryImp.MainRepositoryImpl
import soft.divan.test_factory_hothouse.data.remote.api.ApiFactory
import soft.divan.test_factory_hothouse.data.remote.api.AuthServiceApi
import soft.divan.test_factory_hothouse.domain.repository.AuthRepository
import soft.divan.test_factory_hothouse.domain.repository.MainRepository
import soft.divan.test_factory_hothouse.data.remote.rest.OkHttpClientFactory
import soft.divan.test_factory_hothouse.data.remote.rest.RetrofitFactory
import soft.divan.test_factory_hothouse.data.remote.rest.interseptors.AuthAuthenticator
import soft.divan.test_factory_hothouse.data.remote.rest.interseptors.AuthInterceptor
import soft.divan.test_factory_hothouse.data.remote.rest.interseptors.LoggingInterceptor
import soft.divan.test_factory_hothouse.domain.usecases.CheckAuthCodeUseCase
import soft.divan.test_factory_hothouse.domain.usecases.GetCurrentUserUseCase
import soft.divan.test_factory_hothouse.domain.usecases.RegistrationUserUseCase
import soft.divan.test_factory_hothouse.domain.usecases.SendAuthCodeUseCase

val domainModule = module {


    factory { OkHttpClientFactory(get(), get(), get()) }

    factory { RetrofitFactory(get()) }

    single { LoggingInterceptor() }

    single { AuthInterceptor(get()) }

    /**  single { ApiFactory(get()).authServiceApi }
     * Регистрация зависимостей: Обратите внимание, что AuthServiceApi зарегистрирован отдельно. Теперь,
     * когда AuthAuthenticator будет запрашиваться, он сможет получить AuthServiceApi через функцию-поставщик.
     */
    factory { ApiFactory(get()).authServiceApi }

    factory { AuthAuthenticator(get(), lazy { get () }) }

    single<MainRepository> { MainRepositoryImpl(get()) }

    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    factory { ApiFactory(get()).serverApi }


    single { AuthInterceptor(get()) }


    factory { SendAuthCodeUseCase(get()) }
    factory { CheckAuthCodeUseCase(get()) }
    factory { GetCurrentUserUseCase(get()) }
    factory { RegistrationUserUseCase(get()) }


}