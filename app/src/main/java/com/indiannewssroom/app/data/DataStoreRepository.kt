package com.indiannewssroom.app.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.*
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import com.indiannewssroom.app.util.Constants.Companion.PREFERENCES_CATEGORY_1
import com.indiannewssroom.app.util.Constants.Companion.PREFERENCES_CATEGORY_2
import com.indiannewssroom.app.util.Constants.Companion.PREFERENCES_CATEGORY_3
import com.indiannewssroom.app.util.Constants.Companion.PREFERENCES_CATEGORY_4
import com.indiannewssroom.app.util.Constants.Companion.PREFERENCES_CATEGORY_5
import com.indiannewssroom.app.util.Constants.Companion.PREFERENCES_NAME
import com.indiannewssroom.app.util.Constants.Companion.bollywood_cinema
import com.indiannewssroom.app.util.Constants.Companion.entertainment
import com.indiannewssroom.app.util.Constants.Companion.humour
import com.indiannewssroom.app.util.Constants.Companion.latest_news
import com.indiannewssroom.app.util.Constants.Companion.viral_news
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException


class DataStoreRepository(context: Context) {

    private object PreferenceKeys {
        val homeCategory1 = preferencesKey<String>(PREFERENCES_CATEGORY_1)
        val homeCategory2 = preferencesKey<String>(PREFERENCES_CATEGORY_2)
        val homeCategory3 = preferencesKey<String>(PREFERENCES_CATEGORY_3)
        val homeCategory4 = preferencesKey<String>(PREFERENCES_CATEGORY_4)
        val homeCategory5 = preferencesKey<String>(PREFERENCES_CATEGORY_5)
    }

    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = PREFERENCES_NAME
    )

    suspend fun saveHomeCategories(
        category1: String,
        category2: String,
        category3: String,
        category4: String,
        category5: String
    ) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.homeCategory1] = category1
            preferences[PreferenceKeys.homeCategory2] = category2
            preferences[PreferenceKeys.homeCategory3] = category3
            preferences[PreferenceKeys.homeCategory4] = category4
            preferences[PreferenceKeys.homeCategory5] = category5
        }
    }


    val readHomeCategories: Flow<HomeCategories> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val category1 = preferences[PreferenceKeys.homeCategory1] ?: viral_news.second
            val category2 = preferences[PreferenceKeys.homeCategory2] ?: entertainment.second
            val category3 = preferences[PreferenceKeys.homeCategory3] ?: bollywood_cinema.second
            val category4 = preferences[PreferenceKeys.homeCategory4] ?: latest_news.second
            val category5 = preferences[PreferenceKeys.homeCategory5] ?: humour.second
            HomeCategories(
                category1,
                category2,
                category3,
                category4,
                category5
            )
        }
}

data class HomeCategories(
    val category1: String,
    val category2: String,
    val category3: String,
    val category4: String,
    val category5: String
)