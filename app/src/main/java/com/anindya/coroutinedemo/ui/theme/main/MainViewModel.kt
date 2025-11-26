package com.anindya.coroutinedemo.ui.theme.main

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.anindya.coroutinedemo.data.FakeRepository
import com.anindya.coroutinedemo.data.HydrationRecord
import com.anindya.coroutinedemo.utils.NotificationHelper
import kotlinx.coroutines.launch

data class MainUiState(
    val data: List<HydrationRecord> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = FakeRepository(application.applicationContext)

    var uiState by mutableStateOf(MainUiState())
        private set

    fun loadData() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true, errorMessage = null)

            try {
                val result = repository.getHydrationData()

                uiState = uiState.copy(
                    data = result,
                    isLoading = false
                )

                NotificationHelper.showNotification(
                    getApplication(),
                    "Data Loaded",
                    "Successfully loaded ${result.size} students."
                )

            } catch (e: Exception) {
                uiState = uiState.copy(
                    errorMessage = "Error: ${e.message}",
                    isLoading = false
                )
            }
        }
    }
}