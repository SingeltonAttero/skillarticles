package ru.skillbranch.skillarticles.core

import android.content.Context
import androidx.annotation.StringRes

class ResourceManager(private val context: Context) {
    fun getString(@StringRes stringRes: Int): String = context.getString(stringRes)
    fun getString(@StringRes stringRes: Int, vararg formatArgs: String) = context.getString(stringRes, *formatArgs)
}