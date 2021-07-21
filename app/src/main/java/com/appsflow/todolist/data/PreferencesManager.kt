package com.appsflow.todolist.data

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "PreferencesManager"

private val Context.dataStore by preferencesDataStore("user_preferences")

enum class SortOrder { BY_DATE, BY_NAME }

data class FilterPrefs(val sortOrder: SortOrder, val hideCompleted: Boolean)

@Singleton
class PreferencesManager @Inject constructor(@ApplicationContext context: Context) {
    private val dataStore = context.dataStore

    val preferencesFlow = dataStore.data
        .catch { ex ->
            if (ex is IOException) {
                Log.e(TAG, "Error reading prefs: ", ex)
                emit(emptyPreferences())
            } else {
                throw ex
            }
        }
        .map { preferences ->
            val sortOrder = SortOrder.valueOf(
                preferences[PrefKeys.SORT_ORDER] ?: SortOrder.BY_DATE.name
            )
            val hideCompleted = preferences[PrefKeys.HIDE_COMPLETED] ?: false
            FilterPrefs(sortOrder, hideCompleted)
        }

    suspend fun updateSortOrder(sortOrder: SortOrder) {
        dataStore.edit { prefs ->
            prefs[PrefKeys.SORT_ORDER] = sortOrder.name
        }
    }

    suspend fun updateHideCompleted(hideCompleted: Boolean) {
        dataStore.edit { prefs ->
            prefs[PrefKeys.HIDE_COMPLETED] = hideCompleted
        }
    }

    //this object was created for better readability of code
    private object PrefKeys {
        val SORT_ORDER = stringPreferencesKey("sort_order")
        val HIDE_COMPLETED = booleanPreferencesKey("hide_completed")

    }
}