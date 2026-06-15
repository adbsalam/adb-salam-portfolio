package uk.adbsalam.portfolio.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import uk.adbsalam.portfolio.shared.navigation.mainAppNav.MainNavigationViewModel
import uk.adbsalam.portfolio.theming.ThemeViewModel

val appModule =
    module {
        viewModel { ThemeViewModel(get()) }
        viewModel { MainNavigationViewModel() }
    }

fun initKoin(
    modules: Module,
    config: KoinAppDeclaration? = null,
) {
    startKoin {
        config?.invoke(this)
        modules(appModule, modules)
    }
}
