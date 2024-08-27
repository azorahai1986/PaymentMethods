package com.hernan.paymentmethods.data

import android.content.Context
import android.util.Log
import com.hernan.paymentmethods.core.database.LocalDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object UtilsLocalData {

    var personName: String = ""

    fun getUserName(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            LocalDataStore.getPersonName(context).collect { name ->
                if (name != null) {
                    personName = name
                }
            }
        }
    }
}