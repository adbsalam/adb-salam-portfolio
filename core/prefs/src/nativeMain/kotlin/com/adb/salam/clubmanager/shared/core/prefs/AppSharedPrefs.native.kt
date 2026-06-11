package com.adb.salam.clubmanager.shared.core.prefs

import platform.Foundation.NSUserDefaults

actual class AppSharedPrefs {
    private val defaults = NSUserDefaults.standardUserDefaults()

    actual suspend fun setString(
        key: String,
        value: String,
    ) {
        defaults.setObject(value, forKey = key)
    }

    actual suspend fun setString(
        key: SharedPrefKey,
        value: String,
    ) {
        defaults.setObject(value, forKey = key.name)
    }

    actual suspend fun setInt(
        key: SharedPrefKey,
        value: Int,
    ) {
        defaults.setInteger(value.toLong(), forKey = key.name)
    }

    actual suspend fun setBoolean(
        key: SharedPrefKey,
        value: Boolean,
    ) {
        defaults.setBool(value, forKey = key.name)
    }

    actual suspend fun getString(key: SharedPrefKey): String? = defaults.stringForKey(key.name)

    actual suspend fun getString(key: String): String? = defaults.stringForKey(key)

    actual suspend fun getInt(key: SharedPrefKey): Int? = defaults.integerForKey(key.name).toInt()

    actual suspend fun getBoolean(key: SharedPrefKey): Boolean = defaults.boolForKey(key.name)
}
