package soft.divan.test_factory_hothouse.app


import android.app.Application
import com.orhanobut.hawk.Hawk


import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import soft.divan.test_factory_hothouse.BuildConfig
import soft.divan.test_factory_hothouse.app.di.dataModule
import soft.divan.test_factory_hothouse.app.di.domainModule
import soft.divan.test_factory_hothouse.app.di.presentationModule


class App : Application() {

    companion object {
        lateinit var app: App
    }


    override fun onCreate() {
        super.onCreate()
        app = this
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(app)
            modules(listOf(dataModule, domainModule, presentationModule))
        }

        Hawk.init(applicationContext).build()

    }

}