package uk.adbsalam.portfolio.shared.core.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_prefs")

actual class AppSharedPrefs(
    private val context: Context,
) {
    actual suspend fun setString(
        key: String,
        value: String,
    ) {
        val prefKey = stringPreferencesKey(key)
        context.dataStore.edit { prefs -> prefs[prefKey] = value }
    }

    actual suspend fun setString(
        key: SharedPrefKey,
        value: String,
    ) {
        val prefKey = stringPreferencesKey(key.name)
        context.dataStore.edit { prefs -> prefs[prefKey] = value }
    }

    actual suspend fun setInt(
        key: SharedPrefKey,
        value: Int,
    ) {
        val prefKey = intPreferencesKey(key.name)
        context.dataStore.edit { prefs -> prefs[prefKey] = value }
    }

    actual suspend fun setBoolean(
        key: SharedPrefKey,
        value: Boolean,
    ) {
        val prefKey = booleanPreferencesKey(key.name)
        context.dataStore.edit { prefs -> prefs[prefKey] = value }
    }

    actual suspend fun getString(key: SharedPrefKey): String? {
        val prefKey = stringPreferencesKey(key.name)
        val prefs = context.dataStore.data.first()
        return prefs[prefKey]
    }

    actual suspend fun getString(key: String): String? {
        val prefKey = stringPreferencesKey(key)
        val prefs = context.dataStore.data.first()
        return prefs[prefKey]
    }

    actual suspend fun getInt(key: SharedPrefKey): Int? {
        val prefKey = intPreferencesKey(key.name)
        val prefs = context.dataStore.data.first()
        return prefs[prefKey]
    }

    actual suspend fun getBoolean(key: SharedPrefKey): Boolean {
        val prefKey = booleanPreferencesKey(key.name)
        val prefs = context.dataStore.data.first()
        return prefs[prefKey] ?: false
    }
}
