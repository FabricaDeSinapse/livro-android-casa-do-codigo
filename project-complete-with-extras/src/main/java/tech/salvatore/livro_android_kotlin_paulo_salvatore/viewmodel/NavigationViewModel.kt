package tech.salvatore.livro_android_kotlin_paulo_salvatore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import tech.salvatore.livro_android_kotlin_paulo_salvatore.extensions.rx.CompositeDisposableExtensions.plusAssign
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.CreaturesRepository
import tech.salvatore.livro_android_kotlin_paulo_salvatore.model.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor(
    userRepository: UserRepository,
    creaturesRepository: CreaturesRepository
) : ViewModel() {
    private val _isReady = MutableLiveData<Boolean>()
    val isReady: LiveData<Boolean>
        get() = _isReady

    private val composite = CompositeDisposable()

    init {
        composite +=
            Observable.combineLatest(
                userRepository.user,
                creaturesRepository.creatures
            ) { _, creatures ->
                creatures.count() > 0
            }.subscribe {
                _isReady.postValue(it)
            }
    }

    override fun onCleared() {
        composite.dispose()
    }
}
