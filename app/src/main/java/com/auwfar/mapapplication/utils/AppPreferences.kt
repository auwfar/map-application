package com.auwfar.mapapplication.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

private const val KEY_PREFERENCE = "KEY_PREFERENCE"
class AppPreferences(context: Context) {
    private val preference: SharedPreferences by lazy {
        context.getSharedPreferences(KEY_PREFERENCE, Context.MODE_PRIVATE)
    }

    companion object {
        private const val KEY_USER_NAME = "KEY_USER_NAME"
        private const val KEY_USER_HOBBY = "KEY_USER_HOBBY"
    }

    var userName: String?
        get() = preference.getString(KEY_USER_NAME, null)
        set(value) = preference.edit {
            putString(KEY_USER_NAME, value)
        }

    var userHobby: String?
        get() = preference.getString(KEY_USER_HOBBY, null)
        set(value) = preference.edit {
            putString(KEY_USER_HOBBY, value)
        }
}