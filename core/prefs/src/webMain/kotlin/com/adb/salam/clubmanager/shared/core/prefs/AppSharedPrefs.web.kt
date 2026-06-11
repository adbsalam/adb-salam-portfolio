package com.adb.salam.clubmanager.shared.core.prefs

import kotlinx.browser.window
import org.w3c.dom.get

actual class AppSharedPrefs {
    private val storage = window.localStorage

    actual suspend fun setString(
        key: String,
        value: String,
    ) {
        storage.setItem(key, value)
    }

    actual suspend fun setString(
        key: SharedPrefKey,
        value: String,
    ) {
        storage.setItem(key.name, value)
    }

    actual suspend fun setInt(
        key: SharedPrefKey,
        value: Int,
    ) {
        storage.setItem(key.name, value.toString())
    }

    actual suspend fun setBoolean(
        key: SharedPrefKey,
        value: Boolean,
    ) {
        storage.setItem(key.name, value.toString())
    }

    actual suspend fun getString(key: SharedPrefKey): String? = storage[key.name]

    actual suspend fun getString(key: String): String? = storage[key]

    actual suspend fun getInt(key: SharedPrefKey): Int? = storage[key.name]?.toIntOrNull()

    actual suspend fun getBoolean(key: SharedPrefKey): Boolean = storage[key.name]?.toBooleanStrictOrNull() ?: false
}
