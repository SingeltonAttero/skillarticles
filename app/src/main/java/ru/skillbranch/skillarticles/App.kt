package ru.skillbranch.skillarticles

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import ru.skillbranch.skillarticles.di.AppModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    AppModule.appModule(),
                    AppModule.databaseModule(),
                    AppModule.viewModelModule(),

                    )
            )
        }
    }

}