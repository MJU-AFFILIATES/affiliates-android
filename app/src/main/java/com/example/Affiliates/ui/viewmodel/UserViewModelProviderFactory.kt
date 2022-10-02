package com.example.Affiliates.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.Affiliates.data.repository.UserRepository

@Suppress("UNCHECKED_CAST")
class UserViewModelProviderFactory(
    private val userRepository: UserRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel:: class.java)) {
            return UserViewModel(userRepository) as T
        }
        throw IllegalArgumentException("ViewModel class not found")
    }
}