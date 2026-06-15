package uk.adbsalam.portfolio

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import org.koin.dsl.module
import uk.adbsalam.portfolio.di.initKoin
import uk.adbsalam.portfolio.shared.core.prefs.AppSharedPrefs

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(
        configure = {
            initKoin(
                modules =
                    module {
                        single { AppSharedPrefs() }
                    },
            )
        },
    ) {
        App()
    }
}
