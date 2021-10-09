package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.User
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.UserRepository

class UsersViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = UserRepository(application)

    val users = MutableLiveData<List<User>>()

    val usersWithCreatures = MutableLiveData<List<User>>()

    // TODO: make only LiveData available outside ViewModel

    init {
        // TODO: remove subscription
        repository.usersWithCreatures.subscribe {
            users.postValue(it)
        }
    }
}
