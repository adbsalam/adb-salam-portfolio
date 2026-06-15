package uk.adbsalam.portfolio.shared.navigation

import uk.adbsalam.portfolio.shared.navigation.mainAppNav.MainAppNav
import uk.adbsalam.portfolio.shared.navigation.rootNav.RootNavRoute

fun MutableList<RootNavRoute>.navigateTo(route: RootNavRoute) {
    filter { it != RootNavRoute.MainContent }.forEach {
        remove(it)
    }
    add(route)
}

fun MutableList<MainAppNav>.popToRoot() {
    filter { it != MainAppNav.Home }.forEach {
        remove(it)
    }
}

fun MutableList<MainAppNav>.navigate(nav: MainAppNav) {
    popToRoot()
    add(nav)
}
