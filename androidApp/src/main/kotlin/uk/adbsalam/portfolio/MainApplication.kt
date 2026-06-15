package uk.adbsalam.portfolio

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import uk.adbsalam.portfolio.di.initKoin
import uk.adbsalam.portfolio.shared.core.prefs.AppSharedPrefs

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            modules =
                module {
                    single { AppSharedPrefs(get()) }
                },
        ) {
            androidContext(this@MainApplication)
        }
    }
}
