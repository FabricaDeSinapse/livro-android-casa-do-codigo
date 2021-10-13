package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.extensions.rx.CompositeDisposableExtensions.plusAssign
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.domain.User
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    application: Application,
    repository: UserRepository
) : AndroidViewModel(application) {
    // TODO: Extract to dependency injection
//    private val repository = UserRepository(application)

    private val _user = MutableLiveData<User>()

    val user: LiveData<User>
        get() = _user

    private val composite = CompositeDisposable()

    init {
        composite += repository.user.subscribe {
            _user.postValue(it)
        }
    }

    override fun onCleared() {
        composite.dispose()
    }
}
