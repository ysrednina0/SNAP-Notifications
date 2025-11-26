//package com.anindya.coroutinedemo.data
//import kotlinx.coroutines.delay
//class FakeRepository {
//    // suspend: can be called from a coroutine
//    suspend fun fetchWelcomeMessage(): String {
//        // Simulate a 2-second "network" delay
//        delay(2000L)
//        return "Hello from coroutine! Data loaded successfully."
//    }
//}

package com.anindya.coroutinedemo.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay

data class HydrationRecord(
    val id: Int,
    val type: String,
    val amount: String,
    val time: String
)

class FakeRepository(private val context: Context) {

    suspend fun getHydrationData(): List<HydrationRecord> {

        delay(2000L)

        val jsonString = context.assets.open("hydration.json")
            .bufferedReader()
            .use { it.readText() }

        val type = object : TypeToken<List<HydrationRecord>>() {}.type
        return Gson().fromJson(jsonString, type)
    }
}