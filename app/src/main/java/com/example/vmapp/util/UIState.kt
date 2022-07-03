package com.example.vmapp.util

sealed class UIState {
    class Success<T>(val response: T): UIState()
    class Error(val exception: Exception): UIState()
    object Loading: UIState()
}
