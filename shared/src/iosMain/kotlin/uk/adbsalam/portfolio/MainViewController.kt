package uk.adbsalam.portfolio

import androidx.compose.ui.window.ComposeUIViewController
import org.koin.dsl.module
import uk.adbsalam.portfolio.di.initKoin
import uk.adbsalam.portfolio.shared.core.prefs.AppSharedPrefs
import kotlin.experimental.ExperimentalObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName(swiftName = "KmpBridge")
object KmpBridge {
    fun InitKoinForIos() {
        initKoin(
            module {
                single { AppSharedPrefs() }
            },
        )
    }

    fun MainViewController() =
        ComposeUIViewController {
            App()
        }
}
