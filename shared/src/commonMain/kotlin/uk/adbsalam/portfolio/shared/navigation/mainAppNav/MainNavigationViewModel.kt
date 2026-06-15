package uk.adbsalam.portfolio.shared.navigation.mainAppNav

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainNavigationViewModel : ViewModel() {
    private var _navBarState =
        MutableStateFlow(
            NavBarState(24.dp, 0),
        )
    val navBarState = _navBarState.asStateFlow()

    fun onBackPress() {
        _navBarState.update { it.copy(offset = 24.dp, selectedIndex = 0) }
    }

    fun onIndexNavChanged(
        offset: Dp,
        index: Int,
    ) {
        _navBarState.update {
            it.copy(selectedIndex = index, offset = offset - 44.dp)
        }
    }
}

data class NavBarState(
    val offset: Dp,
    val selectedIndex: Int,
)
