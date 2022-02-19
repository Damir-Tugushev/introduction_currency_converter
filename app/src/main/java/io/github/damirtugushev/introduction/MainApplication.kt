package io.github.damirtugushev.introduction

import android.app.Application
import io.github.damirtugushev.introduction.di.appModule
import io.github.damirtugushev.introduction.di.networkModule
import io.github.damirtugushev.introduction.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@Suppress("unused")
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(applicationContext)
            modules(appModule, repositoryModule, networkModule)
        }
    }
}
