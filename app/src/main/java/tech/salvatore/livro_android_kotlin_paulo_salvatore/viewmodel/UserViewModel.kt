package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.User
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.UserRepository

class UserViewModel(application: Application) : AndroidViewModel(application) {
    // TODO: Extract to dependency injection
    private val repository = UserRepository(application)

    private val _user = MutableLiveData<User>()

    val user: LiveData<User>
        get() = _user

    // TODO: make only LiveData available outside ViewModel

    init {
        // TODO: remove subscription
        repository.user.subscribe {
            _user.postValue(it)
        }
    }
}
