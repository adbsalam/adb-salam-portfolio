package uk.adbsalam.portfolio.theming

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adb.salam.clubmanager.shared.ui.theming.ThemeType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import uk.adbsalam.portfolio.shared.core.prefs.AppSharedPrefs
import uk.adbsalam.portfolio.shared.core.prefs.SharedPrefKey

val LocalNavigationSettings = staticCompositionLocalOf { NavigationSettings() }

sealed interface ThemeState {
    object Loading : ThemeState

    data class Loaded(
        val themeType: ThemeType,
        val animationPref: Boolean,
        val navigationSettings: NavigationSettings,
    ) : ThemeState
}

@Serializable
data class NavigationSettings(
    val blurEnabled: Boolean = false,
    val blurAmount: Float = 10f,
    val alphaEnabled: Boolean = true,
    val alphaAmount: Float = 1f,
)

class ThemeViewModel(
    val sharedPrefs: AppSharedPrefs,
) : ViewModel() {
    private val _themeState = MutableStateFlow<ThemeState>(ThemeState.Loading)
    val themeState = _themeState.asStateFlow()

    fun loadCurrentTheme() {
        viewModelScope.launch {
            _themeState.value =
                ThemeState.Loaded(
                    themeType = sharedPrefs.getString(SharedPrefKey.AppTheme).toAppThemeType(),
                    animationPref = sharedPrefs.getBoolean(SharedPrefKey.AnimationState),
                    navigationSettings = sharedPrefs.getString(SharedPrefKey.NavigationState).toNavigationSettings(),
                )
        }
    }

    fun saveAppTheme(theme: ThemeType) {
        viewModelScope.launch {
            sharedPrefs.setString(SharedPrefKey.AppTheme, theme.name)
            if (_themeState.value is ThemeState.Loaded) {
                val state = _themeState.value as ThemeState.Loaded
                _themeState.value = state.copy(themeType = theme)
            }
        }
    }

    fun saveAnimationPref(animate: Boolean) {
        viewModelScope.launch {
            sharedPrefs.setBoolean(SharedPrefKey.AnimationState, animate)
            if (_themeState.value is ThemeState.Loaded) {
                val state = _themeState.value as ThemeState.Loaded
                _themeState.value = state.copy(animationPref = animate)
            }
        }
    }

    fun saveNavigationSettings(settings: NavigationSettings) {
        viewModelScope.launch {
            saveNavigationAnimation(settings)
            if (_themeState.value is ThemeState.Loaded) {
                val state = _themeState.value as ThemeState.Loaded
                _themeState.value = state.copy(navigationSettings = settings)
            }
        }
    }

    private fun String?.toAppThemeType(): ThemeType =
        if (this.isNullOrEmpty()) {
            ThemeType.SYSTEM
        } else {
            ThemeType.entries.firstOrNull { it.name == this } ?: ThemeType.SYSTEM
        }

    private fun saveNavigationAnimation(navigationSettings: NavigationSettings) {
        viewModelScope.launch {
            sharedPrefs.setString(SharedPrefKey.NavigationState, navigationSettings.toJsonString())
            if (_themeState.value is ThemeState.Loaded) {
                val state = _themeState.value as ThemeState.Loaded
                _themeState.value = state.copy(navigationSettings = navigationSettings)
            }
        }
    }
}

private val json =
    Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
    }

private fun String?.toNavigationSettings(): NavigationSettings =
    this?.let {
        json.decodeFromString<NavigationSettings>(this)
    } ?: NavigationSettings()

private fun NavigationSettings.toJsonString(): String = json.encodeToString(this)
