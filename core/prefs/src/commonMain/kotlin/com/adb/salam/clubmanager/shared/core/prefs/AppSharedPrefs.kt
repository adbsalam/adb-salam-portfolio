package com.adb.salam.clubmanager.shared.core.prefs

expect class AppSharedPrefs {
    suspend fun setString(
        key: String,
        value: String,
    )

    suspend fun setString(
        key: SharedPrefKey,
        value: String,
    )

    suspend fun setInt(
        key: SharedPrefKey,
        value: Int,
    )

    suspend fun setBoolean(
        key: SharedPrefKey,
        value: Boolean,
    )

    suspend fun getString(key: SharedPrefKey): String?

    suspend fun getString(key: String): String?

    suspend fun getInt(key: SharedPrefKey): Int?

    suspend fun getBoolean(key: SharedPrefKey): Boolean
}
