package uk.adbsalam.portfolio.shared.core.prefs

import java.util.prefs.Preferences

actual class AppSharedPrefs {
    private val prefs = Preferences.userRoot().node("app_prefs")

    actual suspend fun setString(
        key: String,
        value: String,
    ) {
        prefs.put(key, value)
    }

    actual suspend fun setString(
        key: SharedPrefKey,
        value: String,
    ) {
        prefs.put(key.name, value)
    }

    actual suspend fun setInt(
        key: SharedPrefKey,
        value: Int,
    ) {
        prefs.putInt(key.name, value)
    }

    actual suspend fun setBoolean(
        key: SharedPrefKey,
        value: Boolean,
    ) {
        prefs.putBoolean(key.name, value)
    }

    actual suspend fun getString(key: SharedPrefKey): String? = prefs.get(key.name, null)

    actual suspend fun getString(key: String): String? = prefs.get(key, null)

    actual suspend fun getInt(key: SharedPrefKey): Int? = prefs.getInt(key.name, 0)

    actual suspend fun getBoolean(key: SharedPrefKey): Boolean = prefs.getBoolean(key.name, false)
}
