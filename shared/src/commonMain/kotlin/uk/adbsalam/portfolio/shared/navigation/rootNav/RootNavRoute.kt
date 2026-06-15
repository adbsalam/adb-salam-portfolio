package uk.adbsalam.portfolio.shared.navigation.rootNav

import kotlinx.serialization.Serializable

@Serializable
sealed interface RootNavRoute {
    @Serializable
    object MainContent : RootNavRoute

    @Serializable
    object ThemeSelection : RootNavRoute

    @Serializable
    object Infographics : RootNavRoute
}
