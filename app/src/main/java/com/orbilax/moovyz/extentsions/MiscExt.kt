package com.orbilax.moovyz.extentsions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// MARK: - Gson
inline fun <reified T> Gson.fromJson(json: String): T {
    return Gson().fromJson(json, object : TypeToken<T>(){}.type)
}