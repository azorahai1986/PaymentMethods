package com.hernan.paymentmethods.core.database

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.hernan.paymentmethods.data.Constants
import com.hernan.paymentmethods.domain.model.CreditCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object LocalDataStore {
    private val Context.dataStore by preferencesDataStore(Constants.DATA_STORE_USER)
    private val CREDIT_CARD_LIST = stringPreferencesKey("creditCardList")

    suspend fun saveCreditCard(context: Context, creditCard: CreditCard) {
        context.dataStore.edit { preferences ->
            val currentListJson = preferences[CREDIT_CARD_LIST] ?: "[]"
            val currentList: MutableList<CreditCard> = Json.decodeFromString(currentListJson)
            currentList.add(creditCard)
            preferences[CREDIT_CARD_LIST] = Json.encodeToString(currentList)
        }
    }

    fun getCreditCard(context: Context): Flow<List<CreditCard>> {
        return context.dataStore.data
            .map { preferences ->
                val currentListJson = preferences[CREDIT_CARD_LIST] ?: "[]"
                Json.decodeFromString(currentListJson)
            }

    }
}