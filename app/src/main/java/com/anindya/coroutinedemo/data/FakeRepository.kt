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

data class Mahasiswa(val id: Int, val nama: String, val jurusan: String)

class FakeRepository(private val context: Context) {

    suspend fun getMahasiswaData(): List<Mahasiswa> {

        delay(2000L)

        val jsonString = context.assets.open("mahasiswa.json")
            .bufferedReader()
            .use { it.readText() }

        val type = object : TypeToken<List<Mahasiswa>>() {}.type
        return Gson().fromJson(jsonString, type)
    }
}