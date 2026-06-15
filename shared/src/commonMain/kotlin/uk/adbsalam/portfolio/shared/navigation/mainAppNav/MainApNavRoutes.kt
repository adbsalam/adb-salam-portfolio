package uk.adbsalam.portfolio.shared.navigation.mainAppNav

import kotlinx.serialization.Serializable

@Serializable
sealed interface MainAppNav {
    val index: Int

    @Serializable
    data object Home : MainAppNav {
        override val index: Int = 0
    }

    @Serializable
    data object ComponentLabs : MainAppNav {
        override val index: Int = 1
    }

    @Serializable
    data object Info : MainAppNav {
        override val index: Int = 2
    }

    @Serializable
    data object Videos : MainAppNav {
        override val index: Int = 3
    }
}
