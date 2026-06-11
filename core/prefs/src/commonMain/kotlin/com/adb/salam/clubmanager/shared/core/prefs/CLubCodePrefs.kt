package com.adb.salam.clubmanager.shared.core.prefs

class CLubCodePrefs(
    private val appSharedPref: AppSharedPrefs,
) {
    suspend fun getClubCode(): String =
        appSharedPref.getString(SharedPrefKey.ClubCode) ?: throw Exception("Should not be able to reach this without a club code")
}
