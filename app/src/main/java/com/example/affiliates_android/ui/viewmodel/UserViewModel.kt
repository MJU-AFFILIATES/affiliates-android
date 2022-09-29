package com.example.affiliates_android.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.affiliates_android.data.model.UserResponse
import com.example.affiliates_android.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.internal.http.hasBody

class UserViewModel(
    private val userRepository: UserRepository
): ViewModel() {

    // Api
    private val _searchResult = MutableLiveData<UserResponse>()
    val searchResult: LiveData<UserResponse> get() = _searchResult

    fun searchUser(personaId: Int) = viewModelScope.launch(Dispatchers.IO) {
        val response = userRepository.searchUser(personaId, "Anna", "Developer")
        if (response.isSuccessful) {
            response.body()?.let { body ->
                _searchResult.postValue(body)
            }
        }
    }



}